package com.cumpleanos.core.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.math.BigInteger;

public record DfacturaDTO(
        Long empresa,
        @JsonFormat(shape = JsonFormat.Shape.STRING) BigInteger cco,
        Long secuencia,
        String barra,
        String producto,
        String item,
        Long cantidad,
        BigDecimal precio,
        BigDecimal total
) {
}
