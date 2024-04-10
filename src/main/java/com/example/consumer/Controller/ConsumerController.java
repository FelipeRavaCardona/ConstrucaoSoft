package com.example.consumer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/consumer")
@AllArgsConstructor
public class ConsumerController {
    @PostMapping
    public void registerConsumer() {

    }

    @PutMapping
    public void modifyConsumer() {

    }

    @GetMapping
    public void getConsumer() {

    }

    @PostMapping("/transactions")
    public void makeTransaction() {

    }

    @PutMapping("/transactions/{transactionId}")
    public void reimburse(@PathVariable("transactionId") String id) {

    }

    @GetMapping("/transactions")
    public void getAllTransactions() {
        
    }
}
