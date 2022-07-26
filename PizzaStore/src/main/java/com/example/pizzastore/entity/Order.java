package com.example.pizzastore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime orderedDateTime;

    private String name;
    private String deliveryAddress;
    private String phoneNumber;

    @OneToMany(mappedBy = "order")
    private List<PizzaOrder> pizzaOrders;

    @OneToMany(mappedBy = "order")
    private List<DrinkOrder> drinkOrders;
}
