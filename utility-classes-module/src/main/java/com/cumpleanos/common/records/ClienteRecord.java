package com.cumpleanos.common.records;

public record ClienteRecord(
        Long empresa,
        Long codigo,
        Short tipo,
        String nombre,
        String rucCedula,
        String direccion
) {}
