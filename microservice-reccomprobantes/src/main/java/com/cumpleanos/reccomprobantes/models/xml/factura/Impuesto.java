package com.cumpleanos.reccomprobantes.models.xml.factura;

import lombok.Data;

@Data
public class Impuesto {
    private String codigo;
    private String codigoPorcentaje;
    private String tarifa;
    private String baseImponible;
    private String valor;
}
