package com.commonservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
@Getter
@Builder
@AllArgsConstructor
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String transactionId;
    private String terminalId;
    private String merchantId;
    private String payMethod;
    private String orderId;
    private String orderName;
    private String productName;
    private Long amount;
    private String van;
    private String vanId;
    private String vanTrxId;
    private String vanResultCode;
    private String vanResultMessage;
    private LocalDateTime tradeDateTime;

    public Payment() {

    }
}
