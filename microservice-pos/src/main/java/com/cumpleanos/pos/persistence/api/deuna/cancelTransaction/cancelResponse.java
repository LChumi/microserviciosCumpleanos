package com.cumpleanos.pos.persistence.api.deuna.cancelTransaction;

public record cancelResponse(
   String transactionId,
   String message
) {}
