package com.example.pizzastore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * The Class Users.
 */
@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The name.
     */
    private String name;

    /**
     * The password.
     */
    private String password;

    /**
     * The roles.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", // name of middlemen table
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;
}
