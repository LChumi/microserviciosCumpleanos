package com.cumpleanos.reccomprobantes.models.xml.retencion;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "comprobanteRetencion")
@XmlAccessorType(XmlAccessType.FIELD)
public class ComprobanteRetencion {
    @XmlAttribute
    private String id;
    @XmlAttribute
    private String version;
    @XmlElement(name = "infoTributaria")
    private InfoTributaria infoTributaria;
    @XmlElement(name = "infoCompRetencion")
    private InfoCompRetencion infoCompRetencion;
    @XmlElement(name = "docsSustento")
    private DocsSustento docsSustento;
    @XmlElement(name = "infoAdicional")
    private InfoAdicional infoAdicional;
}
