package com.fabriciomoreira.exchangerate.client;

import java.math.BigDecimal;
import java.util.Map;


public interface ExternalExchangeRate {
    Map<String, BigDecimal> getRateFromSource();
}
