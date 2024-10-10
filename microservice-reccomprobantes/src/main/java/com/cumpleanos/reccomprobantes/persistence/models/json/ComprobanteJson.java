package com.cumpleanos.reccomprobantes.persistence.models.json;

import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ComprobanteJson implements Comprobante {
    @JsonProperty("RespuestaAutorizacionComprobante")
    private RespuestaAutorizacionComprobante respuestaAutorizacionComprobante;
}
