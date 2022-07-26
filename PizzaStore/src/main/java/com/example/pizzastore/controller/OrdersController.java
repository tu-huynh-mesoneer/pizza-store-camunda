package com.example.pizzastore.controller;

import com.example.pizzastore.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> orders(@PathVariable("orderId") String orderId) throws ChangeSetPersister.NotFoundException {

        return ResponseEntity.ok().body(ordersService.getOdersById(orderId));
    }

    @GetMapping
    public ResponseEntity<?> orders(Pageable pageable) {

        return ResponseEntity.ok().body("ok");
    }

    @PostMapping
    public ResponseEntity<?> orders() {

        return ResponseEntity.ok().body("ok");
    }
}
