package com.cumpleanos.pos.persistence.api.tramaco.consultarUbiGeo;

import com.cumpleanos.pos.persistence.api.tramaco.CuerpoRespuesta;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record RespuestaubicacionGeo(
        CuerpoRespuesta cuerpoRespuesta,
        Provincia[] provincias
) {}
