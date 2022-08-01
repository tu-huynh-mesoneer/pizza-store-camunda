package com.example.pizzastore.service;

import com.example.pizzastore.payload.response.pizza.PizzaResponse;
import com.example.pizzastore.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    private final ModelMapper modelMapper;

    public List<PizzaResponse> getAllPizza(){
        return pizzaRepository.findAll().stream().map(item -> {
            return modelMapper.map(item, PizzaResponse.class);
        }).collect(Collectors.toList());
    }

}
