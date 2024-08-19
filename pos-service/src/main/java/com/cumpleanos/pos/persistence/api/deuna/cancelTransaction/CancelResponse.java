package com.cumpleanos.pos.persistence.api.deuna.cancelTransaction;

public record CancelResponse(
   String transactionId,
   String message
) {}
