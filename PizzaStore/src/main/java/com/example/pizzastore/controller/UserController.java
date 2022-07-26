package com.example.pizzastore.controller;

import com.example.pizzastore.entity.Role;
import com.example.pizzastore.entity.User;
import com.example.pizzastore.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;



    @GetMapping("/test/adduser")
    public String test() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Role role = new Role();
        role.setId(1);
        role.setName("receptionist");
        User user = new User();
        user.setName("tuhuynh");
        user.setPassword(passwordEncoder.encode("12345"));
        Set<Role> listRule = new HashSet<Role>(Arrays.asList(new Role[] { role }));
        user.setRoles(listRule);
        usersService.addUser(user);
        return "test";
    }
}
