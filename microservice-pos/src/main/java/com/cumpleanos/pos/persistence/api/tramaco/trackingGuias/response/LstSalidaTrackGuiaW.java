package com.cumpleanos.pos.persistence.api.tramaco.trackingGuias.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record LstSalidaTrackGuiaW(
         String celularVPN,
         String ciudad,
         String descripcion,
         String estado,
         String fechaHora,
         String transporte,
         String transportista,
         String usuario
){}
