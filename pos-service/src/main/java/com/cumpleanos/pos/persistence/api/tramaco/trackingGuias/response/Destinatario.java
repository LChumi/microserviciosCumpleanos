package com.cumpleanos.pos.persistence.api.tramaco.trackingGuias.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record Destinatario(
         String apellidos,
         String callePrimaria,
         String calleSecundaria,
         String ciRuc,
         String codigoPostal,
         String nombres,
         String numero,
         String referencia,
         String telefono,
         String tipoIden,
         Long codigoParroquia
){}