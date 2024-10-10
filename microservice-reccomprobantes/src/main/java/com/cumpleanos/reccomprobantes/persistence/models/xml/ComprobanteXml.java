package com.cumpleanos.reccomprobantes.persistence.models.xml;

import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.persistence.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.persistence.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.persistence.models.xml.retencion.ComprobanteRetencion;
import com.cumpleanos.reccomprobantes.patterns.visitor.ComprobanteVisitor;
import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Factura.class, NotaCredito.class, ComprobanteRetencion.class})
public abstract class ComprobanteXml implements Comprobante {
    @XmlElement(name = "tipoComprobante")
    private String tipoComprobante;
    @XmlElement(name = "numeroAutorizacion")
    private String numeroAutorizacion;
    @XmlElement(name = "fechaAutorizacion")
    private String fechaAutorizacion;
    @XmlAttribute(name = "id")
    private String id;
    @XmlAttribute(name = "version")
    private String version;
    @XmlElement(name = "infoTributaria")
    private InfoTributaria infoTributaria;
    @XmlElement(name = "infoAdicional")
    private InfoAdicional infoAdicional;

    public abstract void accept(ComprobanteVisitor visitor);
}
