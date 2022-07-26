package com.example.pizzastore.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * The Class Role.
 */
@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/** The name. */
	private String name;

	/** The users. */
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY) // variable mapping of class Student
	private List<User> users;
}
