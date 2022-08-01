package com.example.pizzastore.payload.request.orders;

import com.example.pizzastore.enums.CrustType;
import com.example.pizzastore.enums.SizeType;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pizza {
    private String id;
    @JsonProperty("size")
    @JsonAlias("sizeType")
    private SizeType sizeType;
    @JsonProperty("crust")
    @JsonAlias("crustType")
    private CrustType crustType;
    private int quantity;
}
