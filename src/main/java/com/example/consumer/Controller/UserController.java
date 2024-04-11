package com.example.consumer.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumer.DTOs.ModifyUserDTO;
import com.example.consumer.DTOs.RegisterUserDTO;
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
    public User modifyConsumer(@RequestBody ModifyUserDTO body, @RequestParam("consumerId") String id) {
        return service.modifyUser(body, id);
    }

    @GetMapping
    public User getConsumer(@RequestParam("consumerId") String id) {
        return service.retrieveUser(id);
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
