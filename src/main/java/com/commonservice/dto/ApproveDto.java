package com.commonservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApproveDto {
    private String transactionId;
    private String orderId;
    private String orderName;
    private String productName;
    private Long amount;
    private String installment;
    private String approvalNumber;
    private Card card;
}
