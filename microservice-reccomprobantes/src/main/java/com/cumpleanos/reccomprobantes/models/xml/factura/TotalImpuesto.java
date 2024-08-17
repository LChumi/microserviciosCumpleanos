package com.cumpleanos.reccomprobantes.models.xml.factura;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "totalImpuesto")
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalImpuesto {
    private String codigo;
    private String codigoPorcentaje;
    private String baseImponible;
    private String valor;
}
