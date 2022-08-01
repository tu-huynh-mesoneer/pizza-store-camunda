package com.example.pizzastore.payload.request.orders;

import lombok.Data;

@Data
public class Drink {
    private String id;
    private String description;
    private int quantity;
}
