package com.cumpleanos.reccomprobantes.persistence.models.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class CampoAdicional {
    @XmlAttribute
    private String nombre;
    @XmlAttribute
    private String valor;
}