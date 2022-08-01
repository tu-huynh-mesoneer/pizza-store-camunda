package com.example.pizzastore.service;

import com.example.pizzastore.entity.DrinkOrder;
import com.example.pizzastore.entity.Order;
import com.example.pizzastore.entity.PizzaOrder;
import com.example.pizzastore.entity.User;
import com.example.pizzastore.enums.OrderStatus;
import com.example.pizzastore.payload.request.orders.Drink;
import com.example.pizzastore.payload.request.orders.OrderDetailRequest;
import com.example.pizzastore.payload.request.orders.Pizza;
import com.example.pizzastore.payload.response.orders.OrdersDetailResponse;
import com.example.pizzastore.repository.DrinkRepository;
import com.example.pizzastore.repository.OrderRepository;
import com.example.pizzastore.repository.PizzaRepository;
import com.example.pizzastore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrderRepository orderRepository;

    private final PizzaRepository pizzaRepository;

    private final DrinkRepository drinkRepository;

    private final ModelMapper modelMapper;

    public OrdersDetailResponse getOdersById(String id) throws ChangeSetPersister.NotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        return modelMapper.map(order, OrdersDetailResponse.class);
    }

    public OrdersDetailResponse saveOrder(OrderDetailRequest request) throws ChangeSetPersister.NotFoundException {

        Order order = modelMapper.map(request, Order.class);
        order.setOrderedDateTime(LocalDateTime.now());
        order.setPizzaOrders(setPizzaOrders(request.getPizzas(), order));
        order.setDrinkOrders(setDrinkOrders(request.getDrinks(), order));
        Order orderResponse = orderRepository.save(order);

        OrdersDetailResponse response = modelMapper.map(request, OrdersDetailResponse.class);
        response.setOrderId(orderResponse.getOrderId());
        response.setOrderedDateTime(orderResponse.getOrderedDateTime());
        return response;
    }

    public boolean updateOrderStatus(String orderId, OrderStatus status) throws ChangeSetPersister.NotFoundException {
        try {
            Order order = orderRepository.findById(orderId).get();
            order.setOrderStatus(status);
            orderRepository.save(order);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private List<PizzaOrder> setPizzaOrders(List<Pizza> pizzas, Order order) throws ChangeSetPersister.NotFoundException {
        try {
            if(ObjectUtils.isEmpty(pizzas)) {
                return null;
            }
            return pizzas.stream().map(pizza -> {
                PizzaOrder pizzaOrder = modelMapper.map(pizza, PizzaOrder.class);
                pizzaOrder.setPizza(pizzaRepository.findById(pizza.getId()).get());
                pizzaOrder.setOrder(order);
                return pizzaOrder;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    private List<DrinkOrder> setDrinkOrders(List<Drink> drinks, Order order) throws ChangeSetPersister.NotFoundException {
        try {
            if(ObjectUtils.isEmpty(drinks)) {
                return null;
            }
            return drinks.stream().map(drink -> {
                DrinkOrder drinkOrder = modelMapper.map(drink, DrinkOrder.class);
                drinkOrder.setDrink(drinkRepository.findById(drink.getId()).get());
                drinkOrder.setOrder(order);
                return drinkOrder;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ChangeSetPersister.NotFoundException();
        }
    }
}
