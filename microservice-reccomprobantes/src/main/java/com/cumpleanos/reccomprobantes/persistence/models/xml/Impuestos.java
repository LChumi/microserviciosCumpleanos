package com.cumpleanos.reccomprobantes.persistence.models.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Impuestos {
    @XmlElement(name = "impuesto")
    private List<Impuesto> impuesto;
}
