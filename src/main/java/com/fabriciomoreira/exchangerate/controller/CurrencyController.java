package com.fabriciomoreira.exchangerate.controller;

import com.fabriciomoreira.exchangerate.controller.dto.CurrencyDto;
import com.fabriciomoreira.exchangerate.controller.dto.ExchangeRequestDto;
import com.fabriciomoreira.exchangerate.controller.dto.ExchangeResponseDto;
import com.fabriciomoreira.exchangerate.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping
    @Cacheable("allcurrencies")
    @CrossOrigin(origins = "*")
    public Set<CurrencyDto> findAll() {
        return currencyService.findAllCurrenciesAndRates();
    }

    @PostMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<ExchangeResponseDto> exchangeCalculate(@RequestBody @Valid ExchangeRequestDto exchangeRequestDto) {
        ExchangeResponseDto exchangeResponseDTO = currencyService.exchangeCalculate(exchangeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(currencyService.insertNewExchange(exchangeResponseDTO));
    }

}
