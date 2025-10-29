package com.cumpleanos.pos.persistence.api.tramaco.calcularPrecio.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record LstGuias(
        Long id,
        Double iva,
        Double ivaCalculado,
        Double ivaSeguro
){}
