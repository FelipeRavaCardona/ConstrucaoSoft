package com.example.consumer.Models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

enum TransactionStatus {
    PENDING, CANCELED, SUCCESS
}

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String transactionId;
    Double value;
    LocalDateTime transactionDate;
    
    @Enumerated(EnumType.STRING)
    TransactionStatus status;
    
    // @OneToOne
    String tenantId;

    // @OneToOne
    String buyerId;

    // @OneToOne
    String ticketId;

    public Transaction(Double value, LocalDateTime transactionDate, String tenantId, String buyerId, String ticketId) {
        this.transactionId = UUID.randomUUID().toString();
        this.status = TransactionStatus.PENDING;
        this.value = value;
        this.transactionDate = transactionDate;
        this.tenantId = tenantId;
        this.buyerId = buyerId;
        this.ticketId = ticketId;
    }
}
