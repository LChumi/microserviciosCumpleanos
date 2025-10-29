package com.cumpleanos.reccomprobantes.persistence.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Mensajes {
    @JsonProperty("mensaje")
    private Mensaje mensaje;
}
