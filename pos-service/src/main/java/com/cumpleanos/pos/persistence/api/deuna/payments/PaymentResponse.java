package com.cumpleanos.pos.persistence.api.deuna.payments;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record PaymentResponse(
        String status,
        String transactionId,
        Optional<String> qr,
        Optional<String> deeplink,
        Optional<String> numericCode
) {}
