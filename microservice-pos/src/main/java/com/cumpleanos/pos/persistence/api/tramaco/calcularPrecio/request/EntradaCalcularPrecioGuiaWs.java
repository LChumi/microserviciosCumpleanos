package com.cumpleanos.pos.persistence.api.tramaco.calcularPrecio.request;

public record EntradaCalcularPrecioGuiaWs(
        String codParroquiaRemit,
        LstCargaDestino[] lstCargaDestinos
){}
