package com.example.consumer.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.consumer.Models.User;

@Repository
public interface ConsumerRepo extends JpaRepository<User, String>{
    
}
