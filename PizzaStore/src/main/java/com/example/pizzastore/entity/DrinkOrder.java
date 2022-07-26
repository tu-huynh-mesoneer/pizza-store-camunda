package com.example.pizzastore.entity;

import com.example.pizzastore.entity.idclass.DrinkOrderId;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@IdClass(DrinkOrderId.class)
public class DrinkOrder implements Serializable {


    /*
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    private String description;

    private Integer quantity;
}
