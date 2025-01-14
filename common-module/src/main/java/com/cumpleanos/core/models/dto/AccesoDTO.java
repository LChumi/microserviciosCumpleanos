package com.cumpleanos.core.models.dto;

public record AccesoDTO(
        Long usuario,
        Long empresa,
        Long almacen,
        Long empresaDef,
        Long pVenta
) {
}
