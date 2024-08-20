package com.cumpleanos.reccomprobantes.models.xml.factura;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PagoFactura {
    private String formaPago;
    private String total;
    private String plazo;
    private String unidadTiempo;
}
