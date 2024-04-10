package com.example.consumer.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    private String name;
    private String email;
    private String firebaseToken;
    private String tenantId;
    private String privacyConfigId;
}
