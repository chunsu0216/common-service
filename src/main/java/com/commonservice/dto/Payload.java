package com.commonservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Payload {
    private String transactionId;
    private String method;
    private Integer amount;
    private String orderId;
    private String orderName;
    private String productName;
    private String approvalNumber;
    private String van;
    private String vanId;
    private String vanTrxId;
    private String vanResultCode;
    private String vanResultMessage;
    private String terminalId;
    private String tradeDateTime;
}
