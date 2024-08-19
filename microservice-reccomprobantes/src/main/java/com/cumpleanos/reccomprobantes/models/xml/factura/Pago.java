package com.cumpleanos.reccomprobantes.models.xml.factura;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "pago")
public class Pago {
    private String formaPago;
    private String total;
    private String plazo;
    private String unidadTiempo;
}
