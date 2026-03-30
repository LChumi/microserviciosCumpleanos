package com.cumpleanos.common.records;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.math.BigInteger;

public record ImporItemDTO (
        Long empresa,
        @JsonFormat(shape =  JsonFormat.Shape.STRING)
        BigInteger cco,
        Long proCodigo,
        String barra,
        String proNombre,
        String item,
        Integer cantPedida,
        Integer cantLlegada,
        Integer cantLiquidada,
        BigDecimal costo,
        BigDecimal precio
) {}