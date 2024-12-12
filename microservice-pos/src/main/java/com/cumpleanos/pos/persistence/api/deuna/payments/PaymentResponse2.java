package com.cumpleanos.pos.persistence.api.deuna.payments;

public record PaymentResponse2(
        String status,
        String transactionId,
        String qr,
        String deeplink
) {}
