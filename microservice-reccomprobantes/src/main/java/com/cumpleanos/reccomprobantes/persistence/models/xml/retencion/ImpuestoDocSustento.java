package com.cumpleanos.reccomprobantes.persistence.models.xml.retencion;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ImpuestoDocSustento {
    private String codImpuestoDocSustento;
    private String codigoPorcentaje;
    private String baseImponible;
    private String tarifa;
    private String valorImpuesto;
}
