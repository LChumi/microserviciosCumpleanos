package com.cumpleanos.pos.persistence.api.deuna.cancelTransaction;

public record cancelRequest(
        String id,
        String transactionId
) {}
