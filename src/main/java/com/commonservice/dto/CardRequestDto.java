package com.commonservice.dto;

import lombok.Data;

@Data
public class CardRequestDto {

    private String terminalKey;
    private Long amount;
}
