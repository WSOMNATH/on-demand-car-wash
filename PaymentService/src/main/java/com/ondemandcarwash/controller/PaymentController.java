package com.ondemandcarwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ondemandcarwash.model.Payments;
import com.ondemandcarwash.service.PaymentService;
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    public PaymentService service;

    @PostMapping("/pay")
    public Payments doPayment(@RequestBody Payments payment){
        return service.doPay(payment);
    }}