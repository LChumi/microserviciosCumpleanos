package com.cumpleanos.core.models.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public record DfacturaDto(
        Long empresa,
        BigInteger cco,
        Long secuencia,
        Long barra,
        String producto,
        String item,
        Long cantidad,
        BigDecimal precio,
        BigDecimal total
) {
}
