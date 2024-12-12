package com.cumpleanos.pos.persistence.api.deuna.devolutions;

public record responseOk(
        Boolean status,
        String message,
        String transactionReverseId
) {}
