package com.cumpleanos.common.records;

public record UsrBodDTO(
        Long empresa,
        Long codigo,
        String bodId,
        String nombre,
        String ubicacion,
        Boolean bodDefault) {
}