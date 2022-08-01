package com.example.pizzastorecamundamodeler.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class PizzaListResponse {
    private List<PizzaResponse> pizzaResponse;
}
