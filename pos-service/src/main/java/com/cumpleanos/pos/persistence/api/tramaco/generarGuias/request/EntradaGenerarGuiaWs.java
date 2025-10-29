package com.cumpleanos.pos.persistence.api.tramaco.generarGuias.request;

public record EntradaGenerarGuiaWs(
        LstCargaDestinoG[] lstCargaDestino,
        Remitente remitente
){}
