package com.commonservice.kafka;

import com.commonservice.dto.Payload;
import com.commonservice.entity.Payment;
import com.commonservice.entity.Terminal;
import com.commonservice.repository.PaymentRepository;
import com.commonservice.repository.TerminalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class kafkaConsumer {

    private final ObjectMapper objectMapper;
    private final TerminalRepository terminalRepository;
    private final PaymentRepository paymentRepository;

    /**
     * kafka topic consumer
     * 공통 결제(매입) 내역 생성
     */
    @SneakyThrows
    @Transactional
    @KafkaListener(topics = "approve_result")
    public void createPaymentByCard(String kafkaMessage){

        Payload payload = objectMapper.readValue(kafkaMessage, Payload.class);
        log.info("payload : {}", payload);

        // 공통 정보
        Terminal terminal = terminalRepository.findTerminalByTerminalId(payload.getTerminalId());

        Payment payment = Payment.builder()
                .transactionId(payload.getTransactionId())
                .payMethod("card")
                .terminalId(payload.getTerminalId())
                .merchantId(terminal.getMerchant().getMerchantId())
                .orderId(payload.getOrderId())
                .orderName(payload.getOrderName())
                .productName(payload.getProductName())
                .amount(Long.valueOf(payload.getAmount()))
                .van(payload.getVan())
                .vanId(payload.getVanId())
                .vanTrxId(payload.getVanTrxId())
                .vanResultCode(payload.getVanResultCode())
                .vanResultMessage(payload.getVanResultMessage())
                .tradeDateTime(LocalDateTime.parse(payload.getTradeDateTime()))
                .build();

        paymentRepository.save(payment);
    }

    /**
     * kafka topic consumer
     * 승인 취소
     */
    @SneakyThrows
    @Transactional
    @KafkaListener(topics = "cancel_result")
    public void updatePaymentByCard(String kafkaMessage){

        Payload payload = objectMapper.readValue(kafkaMessage, Payload.class);
        log.info("payload : {}", payload);

        Payment payment = paymentRepository.findPaymentByTransactionId(payload.getTransactionId());
        payment.decreaseAmount(Long.valueOf(payload.getAmount()));
    }
}
