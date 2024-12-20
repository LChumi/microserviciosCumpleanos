package com.cumpleanos.pos.persistence.api.tramaco.consultaLocalidad;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record LstProductoC(
        Long id,
        Object[] lstServicio,
        String nombre,
        Boolean porKilo,
        String tipo
){}
