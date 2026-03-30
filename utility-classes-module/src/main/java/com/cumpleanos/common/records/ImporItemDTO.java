package com.cumpleanos.common.records;

public record ImporItemDTO (
        Long empresa,
        Integer cantPedida,
        Integer cantLlegada,
        Integer cantLiquidada,
        Double costo,
        Double precio
) {}