package com.example.pizzastore.camunda.demo;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;

@Named("reserveSeatBoat")
public class ReserveSeatBoat implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String money = "0.0";
        String ticketType = "Coach";

        money = (String) delegateExecution.getVariable("money");
        double moneyDouble = Double.parseDouble(money);
        if (moneyDouble >= 10000) {
            ticketType = "First Class";
        } else if (moneyDouble >= 5000) {
            ticketType = "Business Class";
        } else if (moneyDouble <= 50) {
            ticketType = "stowaway";
            throw new BpmnError("Fall_overBoard", "A cheap ticket has led to a small wave throwing you over board");
        }

        delegateExecution.setVariable("ticketType", ticketType);
    }
}
