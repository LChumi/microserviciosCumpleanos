package com.cumpleanos.pos.persistence.api.tramaco.generarGuias.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record LstGuiasG(
        Long id,
        String guia
) {}
