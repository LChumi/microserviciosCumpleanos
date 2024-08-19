package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalConImpuestos {
    @XmlElement(name = "totalImpuesto")
    private TotalImpuesto totalImpuesto;
}
