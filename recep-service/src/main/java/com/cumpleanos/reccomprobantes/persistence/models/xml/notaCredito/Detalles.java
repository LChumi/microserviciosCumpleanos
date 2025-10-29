package com.cumpleanos.reccomprobantes.persistence.models.xml.notaCredito;

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
    private List<DetalleNotaCredito> detalle;
}
