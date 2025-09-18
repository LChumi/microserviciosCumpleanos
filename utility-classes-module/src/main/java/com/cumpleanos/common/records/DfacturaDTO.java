package com.cumpleanos.common.records;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public record DfacturaDTO(
        Long empresa,
        @JsonFormat(shape = JsonFormat.Shape.STRING) BigInteger cco,
        Long secuencia,
        LocalDate fecha,
        Long proCodigo,
        String barra,
        //Nombre de Producto
        String producto,
        String item,
        Integer cantidad,
        BigDecimal precio,
        BigDecimal total
) {
}
