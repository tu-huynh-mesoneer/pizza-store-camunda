package com.example.pizzastore.payload.response.orders;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrdersDetailResponse {
    private LocalDateTime orderedDateTime;
    private String orderId;
    private String name;
    private String deliveryAddress;
    private String phoneNumber;
    private List<Pizza> pizzas;
    private List<Drink> drinks;

    @Getter
    @Setter
    public class Drink {
        private String id;
        private String description;
        private int quantity;
    }

    @Getter
    @Setter
    public class Pizza {
        private String id;
        private String size;
        private String crust;
        private int quantity;
    }
}
