package com.commonservice.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "terminal")
@Getter
public class Terminal extends BaseEntity{

    @Id @GeneratedValue
    private Long idx;
    private String terminalId;
    private String terminalName;
    private String terminalKey;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_idx")
    private Merchant merchant;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "van_idx")
    private Van van;
    private String state;
    private Long onceLimit;

}
