package com.dev.picpay.dto;

import java.math.BigDecimal;

import lombok.Getter;

@Getter
public class TransferDTO {
    
    private BigDecimal valueTransfer;
    private Long payer;
    private Long payee;

}
