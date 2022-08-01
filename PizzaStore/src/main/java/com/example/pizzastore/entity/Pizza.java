package com.example.pizzastore.entity;

import com.example.pizzastore.enums.CrustType;
import com.example.pizzastore.enums.SizeType;
import com.example.pizzastore.enums.converter.CrustTypeConverter;
import com.example.pizzastore.enums.converter.SizeTypeConverter;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Pizza implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String description;

    private Double price;

    @Convert(converter = SizeTypeConverter.class)
    private SizeType sizeType;

    @Convert(converter = CrustTypeConverter.class)
    private CrustType crustType;

    @OneToMany(mappedBy = "pizza")
    private List<PizzaOrder> pizzaOrder;

}
