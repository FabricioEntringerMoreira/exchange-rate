package com.fabriciomoreira.exchangerate.controller;

import com.fabriciomoreira.exchangerate.controller.dto.ExchangeResponseDto;
import com.fabriciomoreira.exchangerate.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exchanges")
public class ExchangesController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<ExchangeResponseDto>> finaAllExchanges() {
        return ResponseEntity.status(HttpStatus.OK).body(currencyService.findAllExchanges());
    }
}
