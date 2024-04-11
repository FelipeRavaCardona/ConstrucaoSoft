package com.example.consumer.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakeTransactionDTO {
    @NotEmpty(message = "tenantID  is required")
    private String tenantId;

    @NotEmpty(message = "ticketId is required")
    private String ticketId;

    @NotNull(message = "value is required")
    private Double value;
}
