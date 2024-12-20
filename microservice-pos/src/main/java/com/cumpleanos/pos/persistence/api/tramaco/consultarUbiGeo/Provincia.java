package com.cumpleanos.pos.persistence.api.tramaco.consultarUbiGeo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record Provincia(
        Long id,
        String nombre,
        Canton[] cantones
){}
