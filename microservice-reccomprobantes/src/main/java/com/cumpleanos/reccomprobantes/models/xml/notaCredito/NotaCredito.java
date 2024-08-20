package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import com.cumpleanos.reccomprobantes.models.xml.ComprobanteXml;
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
}
