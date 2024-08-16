package com.cumpleanos.reccomprobantes.models.xml.factura;

import lombok.Data;

@Data
public class Pago {
    private String formaPago;
    private String total;
    private String plazo;
    private String unidadTiempo;
}
