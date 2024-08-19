package com.cumpleanos.pos.persistence.api.tramaco.calcularPrecio.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record LstServicio(
        Double seguro,
        Double subTotal,
        Double subtotaConIva,
        Double subtotalSinIva,
        Double total,
        Double totalCalculado
){}
