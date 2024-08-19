package com.cumpleanos.reccomprobantes.models.xml.retencion;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DocsSustento {
    @XmlElement(name = "docSustento")
    private DocSustento docSustento;
}
