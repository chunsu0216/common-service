package com.commonservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "van")
@Getter
public class Van extends BaseEntity{

    @Id @GeneratedValue
    private Long idx;
    private String method;
    private String van;
    private String vanId;
    private String vanKey;
}
