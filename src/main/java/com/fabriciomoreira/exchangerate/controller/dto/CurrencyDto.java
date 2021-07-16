package com.fabriciomoreira.exchangerate.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CurrencyDto implements Comparable<CurrencyDto> {
    private String code;
    private BigDecimal rate;

    @Override
    public int compareTo(CurrencyDto otherCurrencyDto) {
        return this.code.compareTo(otherCurrencyDto.getCode());
    }
}
