package com.cumpleanos.pos.persistence.api.deuna.payments;

public record PaymentResponse1(
        String status,
        String transactionId,
        String qr
) {}
