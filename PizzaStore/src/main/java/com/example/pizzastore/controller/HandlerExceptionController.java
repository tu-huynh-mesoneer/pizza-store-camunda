package com.example.pizzastore.controller;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerExceptionController {
    @ExceptionHandler({ ChangeSetPersister.NotFoundException.class })
    public ResponseEntity<?> notFoundException(Pageable pageable) {

        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }
}
