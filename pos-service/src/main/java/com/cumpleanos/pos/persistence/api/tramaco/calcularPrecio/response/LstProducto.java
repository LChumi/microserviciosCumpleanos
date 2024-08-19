package com.cumpleanos.pos.persistence.api.tramaco.calcularPrecio.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record LstProducto(
        Long idProducto,
        Double valor,
        Double porcentajeIva,
        Double valorIva,
        Double valorPrima,
        Double valorIvaPrima
){}
