package com.example.pizzastore.repository;

import com.example.pizzastore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/**
	 * Find by name.
	 *
	 * @param username the username
	 * @return the users
	 */
	public User findByName(String username);

}
