package com.example.pizzastore.payload.response.orders;

import com.example.pizzastore.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrdersResponse {

    private String orderId;
    private User user;
    private LocalDateTime orderedDateTime;
    private String name;
    private String deliveryAddress;
    private String phoneNumber;
}
