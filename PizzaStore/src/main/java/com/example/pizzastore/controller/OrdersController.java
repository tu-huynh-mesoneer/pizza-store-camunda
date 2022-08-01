package com.example.pizzastore.controller;

import com.example.pizzastore.enums.OrderStatus;
import com.example.pizzastore.payload.request.orders.OrderConfirmRequest;
import com.example.pizzastore.payload.request.orders.OrderDetailRequest;
import com.example.pizzastore.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/internal/orders")
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
    public ResponseEntity<?> orders(@RequestBody OrderDetailRequest request) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity<>(ordersService.saveOrder(request), HttpStatus.OK);
    }

    @PostMapping("/receptionist/{orderId}")
    public ResponseEntity<?> confirmOrders(@PathVariable("orderId") String orderId, @RequestBody OrderConfirmRequest request) throws ChangeSetPersister.NotFoundException {
        if (request.isOrderConfirmFlag()) {
            return ResponseEntity.ok().body(ordersService.updateOrderStatus(orderId, OrderStatus.confirm));
        } else {
            return ResponseEntity.ok().body(ordersService.updateOrderStatus(orderId, OrderStatus.cancel));
        }
    }

    @PostMapping("/chef/{orderId}")
    public ResponseEntity<?> chefOrders(@PathVariable("orderId") String orderId, @RequestBody OrderConfirmRequest request) throws ChangeSetPersister.NotFoundException {
        if (request.isOrderConfirmFlag()) {
            return ResponseEntity.ok().body(ordersService.updateOrderStatus(orderId, OrderStatus.done));
        } else {
            return ResponseEntity.ok().body(ordersService.updateOrderStatus(orderId, OrderStatus.cancel));
        }
    }

    @PostMapping("/delivery/{orderId}")
    public ResponseEntity<?> deliveryOrders(@PathVariable("orderId") String orderId, @RequestBody OrderConfirmRequest request) throws ChangeSetPersister.NotFoundException {
        if (request.isOrderConfirmFlag()) {
            return ResponseEntity.ok().body(ordersService.updateOrderStatus(orderId, OrderStatus.delivered));
        } else {
            return ResponseEntity.ok().body(ordersService.updateOrderStatus(orderId, OrderStatus.cancel));
        }
    }
}
