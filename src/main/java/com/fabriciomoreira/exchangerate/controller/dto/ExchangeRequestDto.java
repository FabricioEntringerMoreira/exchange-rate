package com.fabriciomoreira.exchangerate.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ExchangeRequestDto {
    private String originCurrency;
    private String targetCurrency;
    private BigDecimal amount;
}
