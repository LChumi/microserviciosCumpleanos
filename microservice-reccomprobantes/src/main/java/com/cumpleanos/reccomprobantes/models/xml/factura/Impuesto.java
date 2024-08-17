package com.cumpleanos.reccomprobantes.models.xml.factura;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Impuesto {
    private Integer codigo; // Cambiado a Integer
    private String codigoPorcentaje;
    private BigDecimal tarifa; // Usar BigDecimal para números decimales
    private BigDecimal baseImponible; // Este es el campo relacionado con "0.9800"
    private BigDecimal valor;
}
