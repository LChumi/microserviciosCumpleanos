package com.cumpleanos.reccomprobantes.persistence.models.xml.retencion;

import com.cumpleanos.reccomprobantes.persistence.models.xml.ComprobanteXml;
import com.cumpleanos.reccomprobantes.patterns.visitor.ComprobanteVisitor;
import jakarta.xml.bind.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@XmlRootElement(name = "comprobanteRetencion")
@XmlAccessorType(XmlAccessType.FIELD)
public class ComprobanteRetencion extends ComprobanteXml {
    @XmlElement(name = "infoCompRetencion")
    private InfoCompRetencion infoCompRetencion;
    @XmlElement(name = "docsSustento")
    private DocsSustento docsSustento;

    @Override
    public void accept(ComprobanteVisitor visitor) {
        visitor.visit(this);
    }
}
