package com.cumpleanos.reccomprobantes.models.xml.factura;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;
@Data
@XmlRootElement(name = "factura")
@XmlAccessorType(XmlAccessType.FIELD)
public class Factura {
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String version;
    @XmlElement(name = "infoTributaria")
    private InfoTributaria infoTributaria;
    @XmlElement(name = "infoFactura")
    private InfoFactura infoFactura;
    @XmlElementWrapper(name = "detalles")
    @XmlElement(name = "detalle")
    private List<Detalle> detalles;
    @XmlElement(name = "infoAdicional")
    private InfoAdicional infoAdicional;
}
