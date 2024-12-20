package com.cumpleanos.pos.persistence.api.tramaco.calcularPrecio.response;

import com.cumpleanos.pos.persistence.api.tramaco.CuerpoRespuesta;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record RespuestaCalcularPrecio(
        CuerpoRespuesta cuerpoRespuesta,
        LstGuias[] lstGuias,
        LstProducto[] lstProducto,
        LstServicio[] lstServicio
        ){}
