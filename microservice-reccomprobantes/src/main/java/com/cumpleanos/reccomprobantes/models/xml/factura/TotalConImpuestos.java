package com.cumpleanos.reccomprobantes.models.xml.factura;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "pagos")
public class TotalConImpuestos {
    @JacksonXmlProperty(localName = "totalImpuesto")
    private TotalImpuesto totalImpuesto;
}
