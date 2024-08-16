package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import lombok.Data;

@Data
public class TotalImpuesto {
    private String codigo;
    private String codigoPorcentaje;
    private String baseImponible;
    private String valor;
}
