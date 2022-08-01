package com.example.pizzastorecamundamodeler.payload.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import spinjar.com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
public class OrderDetailResponse {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime orderedDateTime;
    private String orderId;
    private String name;
    private String deliveryAddress;
    private String phoneNumber;
    private List<Pizza> pizzas;
    private List<Drink> drinks;

    @Getter
    @Setter
    private static class Drink {
        private String id;
        private String description;
        private int quantity;
    }

    @Getter
    @Setter
    private static class Pizza {
        private String id;
        private String size;
        private String crust;
        private int quantity;
    }
}
