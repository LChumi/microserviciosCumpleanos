package com.cumpleanos.pos.persistence.api.tramaco;

import java.util.Optional;

public record CuerpoRespuesta(
        String codigo,
        String mensaje,
        Optional<String> excepcion
) {
}
