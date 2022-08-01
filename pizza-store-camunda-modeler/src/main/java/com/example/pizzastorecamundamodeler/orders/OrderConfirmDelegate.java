package com.example.pizzastorecamundamodeler.orders;

import camundajar.impl.com.google.gson.Gson;
import com.example.pizzastorecamundamodeler.payload.request.OrderDetailRequest;
import com.example.pizzastorecamundamodeler.payload.response.OrderDetailResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Named("orderConfirmDelegate")
@Component
@Log4j2
@RequiredArgsConstructor
public class OrderConfirmDelegate implements JavaDelegate {

    private final RestTemplate restTemplate;

    private final Gson gson;

    private final ObjectMapper mapper;

    private final String url = "http://localhost:8080/api/internal/orders/receptionist/${orderId}";

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderDetail = (String) delegateExecution.getVariable("orderDetail");
        Boolean orderConfirmFlag = (Boolean) delegateExecution.getVariable("orderConfirmFlag");
        OrderDetailResponse orderDetailResponse = mapper.readValue(orderDetail, OrderDetailResponse.class);
        String body = sendRequest(orderDetailResponse, orderConfirmFlag);

        delegateExecution.setVariable("confirm", Boolean.valueOf(body));

    }

    private String sendRequest(OrderDetailResponse orderDetail, Boolean orderConfirmFlag){
        // create url
        Map<String, String> valuesMap = new HashMap<String, String>();
        valuesMap.put("orderId", orderDetail.getOrderId());
        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String resolvedUrl = sub.replace(url);


        // Set request body
        OrderConfirmRequest orderConfirmRequest = new OrderConfirmRequest();
        orderConfirmRequest.setOrderConfirmFlag(orderConfirmFlag);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderConfirmRequest> request = new HttpEntity<OrderConfirmRequest>(orderConfirmRequest, headers);
        ResponseEntity<String> response;
        try {
            response = restTemplate.postForEntity(resolvedUrl, request, String.class);
        } catch (Exception e) {
            throw new BpmnError("Fall_overBoard",
                    "A cheap ticket has led to a small wave throwing you over board");
        }
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new BpmnError("Fall_overBoard",
                    "A cheap ticket has led to a small wave throwing you over board");
        }
        return response.getBody();
    }
}
