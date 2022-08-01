package com.example.pizzastore.payload.response.pizza;

import com.example.pizzastore.enums.CrustType;
import com.example.pizzastore.enums.SizeType;
import lombok.Data;

@Data
public class PizzaResponse {

    private String id;

    private String name;

    private String description;

    private Double price;

    private SizeType sizeType;

    private CrustType crustType;
}
