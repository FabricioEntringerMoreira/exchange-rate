package com.fabriciomoreira.exchangerate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CurrencyNotFound extends RuntimeException {
    public CurrencyNotFound() {
        super("Currency not found.");
    }
}
