package com.example.pizzastore.entity;

import com.example.pizzastore.enums.CrustType;
import com.example.pizzastore.enums.SizeType;
import com.example.pizzastore.enums.converter.CrustTypeConverter;
import com.example.pizzastore.enums.converter.SizeTypeConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
public class Pizza implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
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
