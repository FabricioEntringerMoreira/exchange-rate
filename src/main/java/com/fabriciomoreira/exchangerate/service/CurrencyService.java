package com.fabriciomoreira.exchangerate.service;

import com.fabriciomoreira.exchangerate.controller.dto.CurrencyDto;
import com.fabriciomoreira.exchangerate.controller.dto.ExchangeRequestDto;
import com.fabriciomoreira.exchangerate.controller.dto.ExchangeResponseDto;
import com.fabriciomoreira.exchangerate.domain.model.Exchange;
import com.fabriciomoreira.exchangerate.domain.repository.ExchangeRepository;
import com.fabriciomoreira.exchangerate.exception.CurrencyNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class CurrencyService {

    @Autowired
    ExchangeRepository exchangeRepository;

    @Autowired
    CurrencyAndRateStoreService currencyAndRateStoreService;

    public Set<CurrencyDto> findAllCurrenciesAndRates() {
        Map<String, BigDecimal> externalCurrencyList = currencyAndRateStoreService.getCurrencyAndRateMap();
        return createCurrencyDTO(externalCurrencyList);
    }

    public ExchangeResponseDto exchangeCalculate(ExchangeRequestDto exchangeResponseDTO) {
        if (!currencyAndRateStoreService.getCurrencyAndRateMap().containsKey(exchangeResponseDTO.getTargetCurrency())) {
            throw new CurrencyNotFound();
        }
        BigDecimal rate = currencyAndRateStoreService.getCurrencyAndRateMap()
                .get(exchangeResponseDTO.getTargetCurrency());

        return ExchangeResponseDto.builder()
                .originCurrency(exchangeResponseDTO.getOriginCurrency())
                .targetCurrency(exchangeResponseDTO.getTargetCurrency())
                .rate(rate)
                .amount(exchangeResponseDTO.getAmount())
                .value(exchangeResponseDTO.getAmount().multiply(rate))
                .dataTimeRate(LocalDateTime.now())
                .build();
    }

    public ExchangeResponseDto insertNewExchange(ExchangeResponseDto exchangeResponseDto) {
        Exchange exchange = exchangeRepository.save(ExchangeResponseDto.toEntity(exchangeResponseDto));
        return ExchangeResponseDto.toDto(exchange);
    }

    public List<ExchangeResponseDto> findAllExchanges() {
        List<Exchange> exchangesList = exchangeRepository.findAll();
        List<ExchangeResponseDto> exchangeResponseDtoList = new ArrayList<>();
        for (Exchange exchange : exchangesList) {
            exchangeResponseDtoList.add(ExchangeResponseDto.toDto(exchange));
        }
        return exchangeResponseDtoList;
    }

    private Set<CurrencyDto> createCurrencyDTO(Map<String, BigDecimal> externalRates) {
        Set<CurrencyDto> currencyList = new TreeSet<>();
        for (Map.Entry<String, BigDecimal> cur : externalRates.entrySet()) {
            currencyList.add(CurrencyDto.builder()
                    .code(cur.getKey())
                    .rate(cur.getValue())
                    .build());
        }
        return currencyList;
    }

}
