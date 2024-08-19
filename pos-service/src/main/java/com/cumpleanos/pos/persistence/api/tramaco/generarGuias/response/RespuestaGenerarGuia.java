package com.cumpleanos.pos.persistence.api.tramaco.generarGuias.response;

import com.cumpleanos.pos.persistence.api.tramaco.CuerpoRespuesta;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record RespuestaGenerarGuia(
        CuerpoRespuesta cuerpoRespuesta,
        SalidaGenerarGuiaWs salidaGenerarGuiaWs
){}
