package com.cumpleanos.reccomprobantes.models.xml.retencion;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class PagoRetencion {
    private String formaPago;
    private String total;
}
