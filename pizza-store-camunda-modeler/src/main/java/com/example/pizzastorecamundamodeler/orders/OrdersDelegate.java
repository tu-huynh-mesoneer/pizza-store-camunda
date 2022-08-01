package com.example.pizzastorecamundamodeler.orders;

import camundajar.impl.com.google.gson.Gson;
import com.example.pizzastorecamundamodeler.payload.request.Drink;
import com.example.pizzastorecamundamodeler.payload.request.OrderDetailRequest;
import com.example.pizzastorecamundamodeler.payload.request.Pizza;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.Arrays;

@Named("ordersDelegate")
@Component
@Log4j2
@RequiredArgsConstructor
public class OrdersDelegate implements JavaDelegate {

    private final RestTemplate restTemplate;

    private final Gson gson;

    private final String url = "http://localhost:8080/api/internal/orders";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String pizzas = (String) delegateExecution.getVariable("pizzas");
        String drinks = (String) delegateExecution.getVariable("drinks");
        String name = (String) delegateExecution.getVariable("name");
        String deliveryAddress = (String) delegateExecution.getVariable("deliveryAddress");
        String phoneNumber = (String) delegateExecution.getVariable("phoneNumber");

        Pizza[] pizzasObject = gson.fromJson(pizzas, Pizza[].class);
        Drink[] drinksObject = gson.fromJson(drinks, Drink[].class);

        // Set request body
        OrderDetailRequest orderDetailRequest = new OrderDetailRequest();
        orderDetailRequest.setName(name);
        orderDetailRequest.setDeliveryAddress(deliveryAddress);
        orderDetailRequest.setPhoneNumber(phoneNumber);
        orderDetailRequest.setDrinks(drinksObject == null ? null : Arrays.asList(drinksObject));
        orderDetailRequest.setPizzas(pizzasObject == null ? null : Arrays.asList(pizzasObject));


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderDetailRequest> request = new HttpEntity<OrderDetailRequest>(orderDetailRequest, headers);
        ResponseEntity<String> response;
        try {
            response = restTemplate.postForEntity(url, request, String.class);
        } catch (Exception e) {
            throw new BpmnError("Fall_overBoard",
                    "A cheap ticket has led to a small wave throwing you over board");
        }
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new BpmnError("Fall_overBoard",
                    "A cheap ticket has led to a small wave throwing you over board");
        }
        delegateExecution.setVariable("orderDetail", response.getBody());

    }
}
