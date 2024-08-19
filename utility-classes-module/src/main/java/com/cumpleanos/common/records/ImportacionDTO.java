package com.cumpleanos.common.records;

import java.math.BigInteger;

public record ImportacionDTO(
        BigInteger cco,
        Long empresa,
        String proveedor,
        String observacion
) {
}
