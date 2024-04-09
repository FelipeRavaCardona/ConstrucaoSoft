package com.example.consumer.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/payment-method")
@AllArgsConstructor
public class PaymentController {
    @PostMapping("/")
    @CrossOrigin("*")
    public void addPaymentMethod() {
        
    }

    public void 
}