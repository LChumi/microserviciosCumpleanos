package com.cumpleanos.pos.persistence.api.tramaco.generarGuias.request;

public record Remitente(
         String codigoPostal,
         String nombres,
         Long codigoParroquia,
         String email,
         String apellidos,
         String callePrimaria,
         String telefono,
         String calleSecundaria,
         String tipoIden,
         String referencia,
         String ciRuc,
         String numero
) {}
