package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "notaCredito")
@XmlAccessorType(XmlAccessType.FIELD)
public class NotaCredito {
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String version;
    @XmlElement(name = "infoTributaria")
    private InfoTributaria infoTributaria;
    @XmlElement(name = "infoNotaCredito")
    private InfoNotaCredito infoNotaCredito;
    @XmlElement(name = "detalles")
    private Detalles detalles;
    @XmlElement(name = "infoAdicional")
    private InfoAdicional infoAdicional;
}
