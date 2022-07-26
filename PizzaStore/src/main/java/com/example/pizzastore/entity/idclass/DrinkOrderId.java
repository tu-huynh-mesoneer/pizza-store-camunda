package com.example.pizzastore.entity.idclass;

import com.example.pizzastore.entity.Drink;
import com.example.pizzastore.entity.Order;
import lombok.Data;

import java.io.Serializable;

@Data
public class DrinkOrderId implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private Order order;

    private Drink drink;
}
