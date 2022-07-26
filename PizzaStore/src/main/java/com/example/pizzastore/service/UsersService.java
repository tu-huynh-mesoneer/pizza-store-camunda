package com.example.pizzastore.service;

import com.example.pizzastore.entity.User;
import com.example.pizzastore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }

}
