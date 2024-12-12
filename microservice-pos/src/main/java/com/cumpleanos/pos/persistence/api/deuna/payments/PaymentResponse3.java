package com.cumpleanos.pos.persistence.api.deuna.payments;

public record PaymentResponse3(
        String transactionId,
        String status,
        String numericCode
){}
