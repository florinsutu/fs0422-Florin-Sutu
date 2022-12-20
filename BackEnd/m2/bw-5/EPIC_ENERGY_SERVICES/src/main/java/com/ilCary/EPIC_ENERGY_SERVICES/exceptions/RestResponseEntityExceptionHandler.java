package com.ilCary.EPIC_ENERGY_SERVICES.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> NotFoundException(NotFoundException exception) {

        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND,
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
    
    @ExceptionHandler(AddressAlreadyExistingException.class)
    public ResponseEntity<ErrorMessage> AddressAlreadyExistingException(AddressAlreadyExistingException exception) {

        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT,
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }
}