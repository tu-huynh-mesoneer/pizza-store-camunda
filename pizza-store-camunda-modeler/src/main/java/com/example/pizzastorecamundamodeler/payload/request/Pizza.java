package com.example.pizzastorecamundamodeler.payload.request;

import lombok.Data;

@Data
public class Pizza {
    private String id;
    private String size;
    private String crust;
    private int quantity;
}
