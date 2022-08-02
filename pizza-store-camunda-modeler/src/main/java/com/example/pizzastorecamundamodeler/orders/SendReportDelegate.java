package com.example.pizzastorecamundamodeler.orders;

import camundajar.impl.com.google.gson.Gson;
import com.example.pizzastorecamundamodeler.payload.response.OrderDetailResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;

@Named("orderConfirmDelegate")
@Component
@Log4j2
@RequiredArgsConstructor
public class SendReportDelegate implements JavaDelegate {
    private final RestTemplate restTemplate;

    private final Gson gson;

    private final ObjectMapper mapper;

    private final String url = "http://localhost:8080/api/internal/orders/chef/${orderId}";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderDetail = (String) delegateExecution.getVariable("orderDetail");
        Boolean orderConfirmFlag = (Boolean) delegateExecution.getVariable("orderConfirmFlag");
        OrderDetailResponse orderDetailResponse = mapper.readValue(orderDetail, OrderDetailResponse.class);


        delegateExecution.setVariable("cookDone", true);

    }
}
