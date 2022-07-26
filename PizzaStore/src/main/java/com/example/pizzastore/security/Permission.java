package com.example.pizzastore.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "security.configuration.permission")
@Configuration("permissionProperties")
@Data
public class Permission {

    private String[] all;
    private String[] receptionist;
    private String[] chef;
    private String[] delivery;
}
