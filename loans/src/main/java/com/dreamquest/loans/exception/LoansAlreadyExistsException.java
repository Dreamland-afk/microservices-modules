package com.dreamquest.loans.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class LoansAlreadyExistsException extends RuntimeException{

    public LoansAlreadyExistsException(String errorMessage)
    {
        super(errorMessage);
    }
}
