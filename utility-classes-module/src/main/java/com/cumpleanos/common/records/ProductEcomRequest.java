package com.cumpleanos.common.records;

import java.time.LocalDate;

public record ProductEcomRequest(
        String nombre,
        String sku,
        String precio,
        String descripcion,
        String categoria,
        String subcategoria,
        Integer stock,
        Integer empresa,
        Boolean withIva,
        String precioOferta,
        LocalDate descFechaIni,
        LocalDate descFechaFin
) {}