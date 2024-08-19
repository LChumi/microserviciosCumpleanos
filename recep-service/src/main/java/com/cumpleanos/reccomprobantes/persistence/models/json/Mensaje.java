package com.cumpleanos.reccomprobantes.persistence.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Mensaje {
    @JsonProperty("identificador")
    private String identificador;

    @JsonProperty("mensaje")
    private String mensaje;

    @JsonProperty("informacionAdicional")
    private String informacionAdicional;

    @JsonProperty("tipo")
    private String tipo;

    public String getMensaje(){
        return mensaje + " " + informacionAdicional + " " + tipo;
    }
}
