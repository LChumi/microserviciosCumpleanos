package com.cumpleanos.common.records;

public record AlmacenDTO(
        Long empresa,
        Long codigo,
        String almId,
        String nombre,
        String direccion) {
}
