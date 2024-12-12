package com.cumpleanos.pos.persistence.api.deuna.infoPayments;

public record infoResponse(
        String status,
        String internalTransactionReference,
        Long amount,
        String transactionId,
        String transferNumber,
        String date,
        String branchId,
        String posId,
        String currency,
        String description,
        String ordererName,
        String ordererIdentification
) {
}
