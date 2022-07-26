package com.example.pizzastore.entity.idclass;

import com.example.pizzastore.entity.Order;
import com.example.pizzastore.entity.Pizza;
import lombok.Data;

import java.io.Serializable;

@Data
public class PizzaOrderId implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    private Order order;

    private Pizza pizza;
}
