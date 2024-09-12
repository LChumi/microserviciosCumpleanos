package com.cumpleanos.reccomprobantes.models.xml.factura;

import com.cumpleanos.reccomprobantes.models.xml.ComprobanteXml;
import com.cumpleanos.reccomprobantes.visitor.ComprobanteVisitor;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "factura")
@XmlAccessorType(XmlAccessType.FIELD)
public class Factura extends ComprobanteXml {
    @XmlElement(name = "infoFactura")
    private InfoFactura infoFactura;
    @XmlElementWrapper(name = "detalles")
    @XmlElement(name = "detalle")
    private List<DetalleFactura> detalles;

    @Override
    public void accept(ComprobanteVisitor visitor) {
        visitor.visit(this);
    }
}
