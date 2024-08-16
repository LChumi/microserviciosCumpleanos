package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import lombok.Data;

@Data
public class Impuesto {
    private String codigo;
    private String codigoPorcentaje;
    private String tarifa;
    private String baseImponible;
    private String valor;
}
