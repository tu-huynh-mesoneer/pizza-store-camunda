package com.example.pizzastore.entity;

import com.example.pizzastore.entity.idclass.PizzaOrderId;
import com.example.pizzastore.enums.SizeType;
import com.example.pizzastore.enums.converter.SizeTypeConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@IdClass(PizzaOrderId.class)
public class PizzaOrder implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;

    @Convert(converter = SizeTypeConverter.class)
    private SizeType sizeType;

    private Integer quantity;

}
