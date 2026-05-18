package com.cumpleanos.common.records;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DreposicionDTO(
        Long codigo,
        Long empresa,
        Long cantSol,
        Long cantApr,
        String observacion,
        LocalDate modFecha,
        String usuario,
        BigDecimal precio,
        BigDecimal porcDesc,
        BigDecimal valDesc,
        BigDecimal total,
        BigDecimal cantDisp,
        Long productoId,
        String producto,
        String barra
) {
}
