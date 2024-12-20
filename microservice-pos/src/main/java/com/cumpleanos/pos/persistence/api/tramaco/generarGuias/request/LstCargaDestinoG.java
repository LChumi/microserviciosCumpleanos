package com.cumpleanos.pos.persistence.api.tramaco.generarGuias.request;

public record LstCargaDestinoG(
        Long id,
        DatoAdicional datoAdicional,
        Remitente destinatario,
        CargaG carga
) {}
