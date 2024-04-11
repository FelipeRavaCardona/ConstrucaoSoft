package com.example.consumer.Service;

import org.springframework.stereotype.Service;

import com.example.consumer.DTOs.RegisterUserDTO;
import com.example.consumer.Models.User;
import com.example.consumer.Repository.ConsumerRepo;
import com.example.consumer.Repository.TransactionRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConsumerService {
    private ConsumerRepo consumerRepo;
    private TransactionRepo transactionRepo;

    public User registerUser(RegisterUserDTO data) {
        User user = new User(
            data.getName(), 
            data.getEmail(),
            data.getFirebaseToken(), 
            data.getTenantId(), 
            data.getPrivacyConfigId()
        );
        
        return consumerRepo.save(user);
    }

    // public User modifyUser() {

    // }
}
