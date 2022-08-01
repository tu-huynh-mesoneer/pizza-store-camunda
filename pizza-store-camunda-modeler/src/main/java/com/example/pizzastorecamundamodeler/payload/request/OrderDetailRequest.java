package com.example.pizzastorecamundamodeler.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderDetailRequest {
    private String name;
    private String deliveryAddress;
    private String phoneNumber;
    private List<Pizza> pizzas;
    private List<Drink> drinks;

}
