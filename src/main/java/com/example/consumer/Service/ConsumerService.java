package com.example.consumer.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.consumer.DTOs.MakeTransactionDTO;
import com.example.consumer.DTOs.RegisterUserDTO;
import com.example.consumer.Models.Transaction;
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

    public Transaction makeTransaction(MakeTransactionDTO data, String consumerId) {
        // must verify if the user exist. 
        // Shouldnt it be storing a User (relationship.)
        Transaction transaction = new Transaction(
            data.getValue(),
            data.getTenantId(),
            consumerId,
            data.getTicketId()
        );
        
        return transactionRepo.save(transaction);
    }

    public List<Transaction> getConsumerTransactions(String consumerId) {
        return transactionRepo.findByBuyerId(consumerId);
    }
}
