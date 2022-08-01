package com.example.pizzastorecamundamodeler.payload.response;

import com.example.pizzastorecamundamodeler.enums.CrustType;
import com.example.pizzastorecamundamodeler.enums.SizeType;
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
