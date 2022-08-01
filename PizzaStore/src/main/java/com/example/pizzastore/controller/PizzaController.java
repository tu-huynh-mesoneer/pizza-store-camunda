package com.example.pizzastore.controller;

import com.example.pizzastore.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/api/pizza", "/api/internal/pizza"})
public class PizzaController {
    private final PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<?> pizza() {
        return new ResponseEntity<>(pizzaService.getAllPizza(), HttpStatus.OK);
    }

}
