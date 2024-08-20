package com.cumpleanos.reccomprobantes.models.xml.retencion;

import com.cumpleanos.reccomprobantes.models.xml.ComprobanteXml;
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
}
