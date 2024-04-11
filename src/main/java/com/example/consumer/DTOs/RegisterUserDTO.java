package com.example.consumer.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDTO {
    @NotEmpty(message = "name is required")
    private String name;

    @NotEmpty(message = "email is required")
    @Email(message = "the provided email is not valid")
    private String email;

    @NotEmpty(message = "firebaseToken is required")
    private String firebaseToken;

    @NotEmpty(message = "tenantId is required")
    private String tenantId;

    @NotEmpty(message = "privacyConfigId is required")
    private String privacyConfigId;
}
