package com.cumpleanos.pos.persistence.api.deuna.payments;

import java.util.Optional;

public record PaymentResponse(
        String status,
        String transactionId,
        Optional<String> qr,
        Optional<String> deeplink,
        Optional<String> numericCode
) {}
