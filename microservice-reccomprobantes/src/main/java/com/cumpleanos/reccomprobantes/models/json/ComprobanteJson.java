package com.cumpleanos.reccomprobantes.models.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ComprobanteJson {
    @JsonProperty("RespuestaAutorizacionComprobante")
    private RespuestaAutorizacionComprobante respuestaAutorizacionComprobante;
}
