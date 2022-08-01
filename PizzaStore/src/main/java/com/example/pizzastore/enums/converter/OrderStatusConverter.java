package com.example.pizzastore.enums.converter;

import com.example.pizzastore.enums.OrderStatus;
import com.example.pizzastore.enums.base.EnumConverter;

public class OrderStatusConverter extends EnumConverter<OrderStatus> {
    public OrderStatusConverter() {
        super(OrderStatus.class);
    }
}
