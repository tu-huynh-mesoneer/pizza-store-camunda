package com.example.pizzastore.service;

import com.example.pizzastore.entity.Order;
import com.example.pizzastore.payload.response.orders.OrdersDetailResponse;
import com.example.pizzastore.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    public OrdersDetailResponse getOdersById(String id) throws ChangeSetPersister.NotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() ->new ChangeSetPersister.NotFoundException());
        OrdersDetailResponse ordersDetail = modelMapper.map(order, OrdersDetailResponse.class);
        return ordersDetail;
    }

}
