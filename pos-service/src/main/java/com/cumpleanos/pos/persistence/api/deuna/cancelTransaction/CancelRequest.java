package com.cumpleanos.pos.persistence.api.deuna.cancelTransaction;

public record CancelRequest(
        String id,
        String transactionId
) {}
