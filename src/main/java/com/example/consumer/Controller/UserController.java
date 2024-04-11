package com.example.consumer.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer.DTOs.MakeTransactionDTO;
import com.example.consumer.DTOs.RegisterUserDTO;
import com.example.consumer.Models.Transaction;
import com.example.consumer.Models.User;
import com.example.consumer.Service.ConsumerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/consumer")
@AllArgsConstructor
public class UserController {
    private ConsumerService service;

    @PostMapping
    public User registerConsumer(@Valid @RequestBody RegisterUserDTO body) {
        return service.registerUser(body);
    }

    @PutMapping
    public void modifyConsumer() {

    }

    @GetMapping
    public void getConsumer() {

    }

    @PutMapping("/transactions/{transactionId}")
    public void reimburse(@PathVariable("transactionId") String id) {

    }

    @PostMapping("/transactions")
    public Transaction makeTransaction(@Valid @RequestBody MakeTransactionDTO body,
            @RequestParam("consumerId") String consumerId) {
        return service.makeTransaction(body, consumerId);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(@RequestParam("consumerId") String consumerId) {
        return service.getConsumerTransactions(consumerId);
    }
}
