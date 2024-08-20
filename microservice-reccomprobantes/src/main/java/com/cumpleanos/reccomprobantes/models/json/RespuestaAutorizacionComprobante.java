package com.cumpleanos.reccomprobantes.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RespuestaAutorizacionComprobante {
    @JsonProperty("claveAccesoConsultada")
    private String claveAccesoConsultada;
    @JsonProperty("numeroComprobantes")
    private String numeroComprobantes;
    @JsonProperty("autorizaciones")
    private Autorizaciones autorizaciones;
}
