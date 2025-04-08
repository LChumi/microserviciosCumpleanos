package com.cumpleanos.common.records;

public record BodegaDTO(
        Long empresa,
        Long codigo,
        String bodId,
        String nombre,
        String ubicacion,
        Boolean bodDefault) {
}
