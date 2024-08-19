package com.cumpleanos.reccomprobantes.models.xml.factura;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "pagos")
public class Pagos {
    private Pago pago;
}
