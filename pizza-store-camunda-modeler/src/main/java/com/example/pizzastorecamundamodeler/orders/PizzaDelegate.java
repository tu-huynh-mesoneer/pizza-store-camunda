package com.example.pizzastorecamundamodeler.orders;

import camundajar.impl.com.google.gson.Gson;
import com.example.pizzastorecamundamodeler.payload.response.PizzaListResponse;
import com.example.pizzastorecamundamodeler.payload.response.PizzaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.ArrayList;

@Named("pizzaDelegate")
@Component
@Log4j2
@RequiredArgsConstructor
public class PizzaDelegate implements JavaDelegate {

    private final RestTemplate restTemplate;

    private final Gson gson;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        final String uri = "http://localhost:8080/api/internal/pizza";
        String result = restTemplate.getForObject(uri, String.class);
        PizzaResponse[] pizzaListResponse = gson.fromJson(result, PizzaResponse[].class);
        delegateExecution.setVariable("pizzaList", result);

    }

}
