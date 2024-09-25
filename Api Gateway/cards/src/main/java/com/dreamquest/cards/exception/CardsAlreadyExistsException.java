package com.dreamquest.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class CardsAlreadyExistsException extends RuntimeException{

    public CardsAlreadyExistsException(String errorMessage)
    {
        super(errorMessage);
    }
}
