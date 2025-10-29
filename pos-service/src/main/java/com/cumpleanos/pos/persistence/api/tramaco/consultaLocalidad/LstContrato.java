package com.cumpleanos.pos.persistence.api.tramaco.consultaLocalidad;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record LstContrato(
        Boolean estado,
        Long id,
        LstProductoC[] lstProducto,
        String numero
){}
