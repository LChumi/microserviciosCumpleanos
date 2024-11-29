package com.cumpleanos.core.models.dto;

public record ClienteRecord(
        Long empresa,
        Long codigo,
        Short tipo,
        String nombre,
        String rucCedula,
        String direccion
) {}
