package com.cumpleanos.pos.persistence.api.tramaco.generarGiaPdf;

import com.cumpleanos.pos.persistence.api.tramaco.CuerpoRespuesta;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record RespuestaGenerarGuiaPdf(
        CuerpoRespuesta cuerpoRespuesta,
        byte[] inStrPfd
){}
