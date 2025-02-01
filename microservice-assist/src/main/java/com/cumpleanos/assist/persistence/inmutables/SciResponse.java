package com.cumpleanos.assist.persistence.inmutables;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;

public record SciResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING) BigInteger cco,
        String comprobante
) {
}