package com.example.consumer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer.DTOs.RegisterUserDTO;
import com.example.consumer.Service.ConsumerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/consumer")
@AllArgsConstructor
public class UserController {
    private ConsumerService service;

    @PostMapping
    public void registerConsumer(@RequestBody RegisterUserDTO body) {
        service.registerUser(body);
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
