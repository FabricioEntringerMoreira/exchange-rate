package com.fabriciomoreira.exchangerate.domain.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String originCurrency;
    private String targetCurrency;
    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal value;
    private LocalDateTime dataTimeRate;
}
