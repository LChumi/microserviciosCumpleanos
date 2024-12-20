package com.cumpleanos.pos.persistence.api.tramaco.authentication.response;

import com.cumpleanos.pos.persistence.api.tramaco.CuerpoRespuesta;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record RespuestaAutenticarWs(
        CuerpoRespuesta cuerpoRespuesta,
        SalidaAutenticarUsuarioJWTWs salidaAutenticarUsuarioJWTWs
){}
