package com.dreamquest.account.exception;

import com.dreamquest.account.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorResponseDto> handleGlobalExceptions (Exception exception , WebRequest webRequest)
    {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false).replace("uri=", ""),
                HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExists (CustomerAlreadyExistsException exception , WebRequest webRequest)
    {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false).replace("uri=", ""),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExists (ResourceNotFoundException exception , WebRequest webRequest)
    {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false).replace("uri=", ""),
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
    }
}
