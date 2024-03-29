package com.commonservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "merchant")
@Getter
public class Merchant extends BaseEntity{

    @Id @GeneratedValue
    private Long idx;
    private String merchantId;
    private String merchantName;
    private String state;
    private Long totalLimit;
}
