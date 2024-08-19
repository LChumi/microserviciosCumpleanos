package com.cumpleanos.pos.persistence.api.tramaco.consultaLocalidad;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record LstLocalidad(
        Boolean activo,
        String callePrimaria,
        String calleSecundaria,
        String codigoPostal,
        Long id,
        Long idParroquia,
        String nombre,
        String numero,
        String referencia,
        Object[] rutas
){}
