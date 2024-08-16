package com.cumpleanos.reccomprobantes.models.xml.retencion;

import lombok.Data;

@Data
public class ImpuestoDocSustento {
    private String codImpuestoDocSustento;
    private String codigoPorcentaje;
    private String baseImponible;
    private String tarifa;
    private String valorImpuesto;
}
