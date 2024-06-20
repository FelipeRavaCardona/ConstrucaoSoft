package com.example.consumer.Models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String userId;
    String name;
    String email;
    String firebaseToken;

    // @OneToOne
    String tenantId;

    // @OneToOne
    String privacyConfigId;

    public User(String name, String email, String firebaseToken, String tenantId, String privacyConfigId) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.firebaseToken = firebaseToken;
        this.tenantId = tenantId;
        this.privacyConfigId = privacyConfigId;
    }
}
