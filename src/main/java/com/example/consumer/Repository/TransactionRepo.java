package com.example.consumer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.consumer.Models.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, String>{
    List<Transaction> findByBuyerId(String buyerId);
}
