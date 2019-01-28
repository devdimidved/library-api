package com.devdimidved.libraryapi.service;

import com.devdimidved.libraryapi.service.exception.BookNotFoundException;
import com.devdimidved.libraryapi.service.exception.DuplicateRentException;
import com.devdimidved.libraryapi.service.exception.NotEnoughMoneyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            BookNotFoundException.class,
            DuplicateRentException.class,
            NotEnoughMoneyException.class
    })
    public ResponseEntity<Object> handleCustomException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
