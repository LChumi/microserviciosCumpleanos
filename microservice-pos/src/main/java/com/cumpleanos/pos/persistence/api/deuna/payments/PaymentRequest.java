package com.cumpleanos.pos.persistence.api.deuna.payments;

public record PaymentRequest(
        String pointOfSale,
        String qrType,
        long amount,
        String detail,
        String internalTransactionReference,
        String format
){}
