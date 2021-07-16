package com.fabriciomoreira.exchangerate.service;

import com.fabriciomoreira.exchangerate.controller.dto.ExchangeRequestDto;
import com.fabriciomoreira.exchangerate.exception.CurrencyNotFound;
import lombok.val;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
public class CurrencyServiceTest {

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    public static final String EUR = "EUR";
    public static final String USD = "USD";
    public static final String BRL = "BRL";
    @InjectMocks
    CurrencyService currencyService;

    @Mock
    CurrencyAndRateStoreService currencyAndRateStoreService;

    @Test
    public void shouldThrowExceptionWhenCurrencyNotFound() {
        Map<String, BigDecimal> mockMap = new HashMap<>();
        mockMap.put(EUR, BigDecimal.ZERO);

        Mockito.when(currencyAndRateStoreService.getCurrencyAndRateMap()).thenReturn(mockMap);

        val exchangeRequestDto = ExchangeRequestDto.builder()
                .originCurrency(EUR)
                .targetCurrency(BRL)
                .amount(BigDecimal.TEN)
                .build();

        Assertions.assertThrows(CurrencyNotFound.class, () -> currencyService.exchangeCalculate(exchangeRequestDto));
    }

    @Test
    public void exchangeShouldBeCalculatedCorrect() {
        Map<String, BigDecimal> mockMap = new HashMap<>();
        mockMap.put(EUR, BigDecimal.ONE);
        mockMap.put(USD, BigDecimal.TEN);

        Mockito.when(currencyAndRateStoreService.getCurrencyAndRateMap()).thenReturn(mockMap);

        ExchangeRequestDto exchangeRequestDto = ExchangeRequestDto.builder()
                .originCurrency(EUR)
                .targetCurrency(USD)
                .amount(BigDecimal.TEN)
                .build();

        Assertions.assertEquals(0, currencyService.exchangeCalculate(exchangeRequestDto)
                .getValue()
                .compareTo(HUNDRED));
    }
}
