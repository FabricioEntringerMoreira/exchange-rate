package com.fabriciomoreira.exchangerate.service;

import com.fabriciomoreira.exchangerate.client.ExternalExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CurrencyAndRateStoreService {

    @Autowired
    ExternalExchangeRate externalExchangeRate;

    @Cacheable("currencysoucercache")
    public Map<String, BigDecimal> getCurrencyAndRateMap() {
        return externalExchangeRate.getRateFromSource();
    }
}
