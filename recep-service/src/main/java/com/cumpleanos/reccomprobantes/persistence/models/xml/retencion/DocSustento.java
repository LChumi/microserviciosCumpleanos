package com.cumpleanos.reccomprobantes.persistence.models.xml.retencion;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DocSustento {
    private String codSustento;
    private String codDocSustento;
    private String numDocSustento;
    private String fechaEmisionDocSustento;
    private String pagoLocExt;
    private String totalSinImpuestos;
    private String importeTotal;
    @XmlElement(name = "impuestosDocSustento")
    private ImpuestosDocSustento impuestosDocSustento;
    @XmlElement(name = "retenciones")
    private Retenciones retenciones;
    @XmlElement(name = "pagos")
    private Pagos pagos;
}
