package com.cumpleanos.reccomprobantes.models.xml.factura;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "pagos")
@XmlAccessorType(XmlAccessType.FIELD)
public class Pagos {
    private Pago pago;
}
