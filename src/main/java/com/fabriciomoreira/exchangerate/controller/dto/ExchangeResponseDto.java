package com.fabriciomoreira.exchangerate.controller.dto;

import com.fabriciomoreira.exchangerate.domain.model.Exchange;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ExchangeResponseDto {
    private Long id;
    private String originCurrency;
    private String targetCurrency;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal value;
    private LocalDateTime dataTimeRate;

    public static Exchange toEntity(ExchangeResponseDto dto) {
        Exchange exchange = new Exchange();

        exchange.setId(dto.getId());
        exchange.setOriginCurrency(dto.getOriginCurrency());
        exchange.setTargetCurrency(dto.getTargetCurrency());
        exchange.setRate(dto.getRate());
        exchange.setAmount(dto.getAmount());
        exchange.setValue(dto.getValue());
        exchange.setDataTimeRate(dto.getDataTimeRate());

        return exchange;
    }

    public static ExchangeResponseDto toDto(Exchange exchange) {
        return ExchangeResponseDto.builder()
                .id(exchange.getId())
                .originCurrency(exchange.getOriginCurrency())
                .targetCurrency(exchange.getTargetCurrency())
                .rate(exchange.getRate())
                .amount(exchange.getAmount())
                .value(exchange.getValue())
                .dataTimeRate(exchange.getDataTimeRate())
                .build();
    }
}
