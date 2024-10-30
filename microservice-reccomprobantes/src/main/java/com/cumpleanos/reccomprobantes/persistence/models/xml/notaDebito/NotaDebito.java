package com.cumpleanos.reccomprobantes.persistence.models.xml.notaDebito;

import com.cumpleanos.reccomprobantes.patterns.visitor.ComprobanteVisitor;
import com.cumpleanos.reccomprobantes.persistence.models.xml.ComprobanteXml;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@Data
@XmlRootElement(name = "notaDebito")
@XmlAccessorType(XmlAccessType.FIELD)
public class NotaDebito extends ComprobanteXml {
    @XmlElement(name = "infoNotaDebito")
    private InfoNotaDebito infoNotaDebito;
    @XmlElement(name = "motivos")
    private Motivos motivos;

    @Override
    public void accept(ComprobanteVisitor visitor) {
        visitor.visit(this);
    }
}
