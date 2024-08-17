package com.cumpleanos.reccomprobantes.models.xml.factura;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@XmlRootElement(name = "detalles")
@XmlAccessorType(XmlAccessType.FIELD)
public class Detalles {
    @XmlElement(name = "detalle")
    private List<Detalle> detalle;
}
