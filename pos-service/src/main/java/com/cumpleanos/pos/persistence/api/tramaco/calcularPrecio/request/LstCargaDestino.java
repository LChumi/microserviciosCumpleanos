package com.cumpleanos.pos.persistence.api.tramaco.calcularPrecio.request;

public record LstCargaDestino(
        Carga carga,
        String codParroquiaDest,
        String id
) {}
