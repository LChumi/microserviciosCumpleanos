package com.cumpleanos.pos.persistence.api.deuna.devolutions;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record DevolutionResponse(
        Boolean status,
        String message,
        String transactionReverseId,
        String statusCode
) {}
