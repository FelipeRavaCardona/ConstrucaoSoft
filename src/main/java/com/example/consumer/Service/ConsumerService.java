package com.example.consumer.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.consumer.DTOs.ModifyUserDTO;
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

    public User modifyUser(ModifyUserDTO data, String id) {
        Optional<User> userOptional = consumerRepo.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (data.getName() != null) {
                user.setName(data.getName());
            }
    
            if (data.getEmail() != null) {
                user.setEmail(data.getEmail());
            }
    
            return consumerRepo.save(user);
        } else {
            return null;
        }
    }

    public User retrieveUser(String id) {
        Optional<User> userOptional = consumerRepo.findById(id);

        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null;
        }
    }
}
