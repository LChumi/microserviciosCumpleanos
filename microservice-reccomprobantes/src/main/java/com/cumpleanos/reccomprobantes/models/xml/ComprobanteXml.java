package com.cumpleanos.reccomprobantes.models.xml;

import com.cumpleanos.reccomprobantes.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Factura.class, NotaCredito.class, ComprobanteRetencion.class})
public abstract class ComprobanteXml implements Comprobante {
    @XmlElement(name = "tipoComprobante")
    private String tipoComprobante;
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "version")
    private String version;
    @XmlElement(name = "infoTributaria")
    private InfoTributaria infoTributaria;
    @XmlElement(name = "infoAdicional")
    private InfoAdicional infoAdicional;
}