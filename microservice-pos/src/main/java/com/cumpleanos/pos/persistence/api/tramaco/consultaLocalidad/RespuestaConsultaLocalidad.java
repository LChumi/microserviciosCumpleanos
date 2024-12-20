package com.cumpleanos.pos.persistence.api.tramaco.consultaLocalidad;

import com.cumpleanos.pos.persistence.api.tramaco.CuerpoRespuesta;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record RespuestaConsultaLocalidad(
        CuerpoRespuesta cuerpoRespuesta,
        SalidaConsultarLocalidadContratoWs salidaConsultarLocalidadContratoWs
){}
