package com.cumpleanos.reccomprobantes.models.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autorizacion {
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("fechaAutorizacion")
    private String fechaAutorizacion;
    private String numeroAutorizacion;
    @JsonProperty("ambiente")
    private String ambiente;
    @JsonProperty("comprobante")
    private String comprobante; // Aquí manejamos el XML como una cadena simple
    @JsonProperty("mensajes")
    private Mensajes mensajes;
}
