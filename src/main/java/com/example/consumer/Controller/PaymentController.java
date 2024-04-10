package com.example.consumer.Controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/payment-methods")
@AllArgsConstructor
public class PaymentController {
    @PostMapping
    public void addPaymentMethod() {
        
    }

    @DeleteMapping("/{paymentMethodId}")
    public void removePaymentMethod(@PathVariable("paymentMethodId") String id) {
        
    }

    @GetMapping
    public void getAllPaymentMethods() {
    
    }
}