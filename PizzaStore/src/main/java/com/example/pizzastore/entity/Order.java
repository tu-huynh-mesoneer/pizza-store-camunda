package com.example.pizzastore.entity;

import com.example.pizzastore.enums.OrderStatus;
import com.example.pizzastore.enums.converter.OrderStatusConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime orderedDateTime;

    private String name;
    private String deliveryAddress;
    private String phoneNumber;

    private Integer receptionistId;

    private Integer chefId;

    private Integer deliverId;

    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<PizzaOrder> pizzaOrders;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<DrinkOrder> drinkOrders;
}
