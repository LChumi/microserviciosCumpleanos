package com.cumpleanos.reccomprobantes.models.json;

import com.cumpleanos.reccomprobantes.models.entity.Comprobante;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ComprobanteJson implements Comprobante {
    @JsonProperty("RespuestaAutorizacionComprobante")
    private RespuestaAutorizacionComprobante respuestaAutorizacionComprobante;
}
