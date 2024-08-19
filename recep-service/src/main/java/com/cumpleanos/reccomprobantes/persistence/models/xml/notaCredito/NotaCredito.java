package com.cumpleanos.reccomprobantes.persistence.models.xml.notaCredito;

import com.cumpleanos.reccomprobantes.persistence.models.xml.ComprobanteXml;
import com.cumpleanos.reccomprobantes.patterns.visitor.ComprobanteVisitor;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "notaCredito")
@XmlAccessorType(XmlAccessType.FIELD)
public class NotaCredito extends ComprobanteXml {
    @XmlElement(name = "infoNotaCredito")
    private InfoNotaCredito infoNotaCredito;
    @XmlElement(name = "detalles")
    private Detalles detalles;

    @Override
    public void accept(ComprobanteVisitor visitor) {
        visitor.visit(this);
    }
}
