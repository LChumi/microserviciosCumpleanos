package com.cumpleanos.common.records;

public record PuntoVentaDTO(
        Long empresa,
        Long almacen,
        Long secuencia,
        String nombre,
        String pveId,
        Boolean inactivo,
        Short multiagente,
        Boolean electronico,
        Boolean reporte
        ) {
}
