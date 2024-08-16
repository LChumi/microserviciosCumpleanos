package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import lombok.Data;

@Data
public class Detalle {
    private String codigoInterno;
    private String codigoAdicional;
    private String descripcion;
    private String cantidad;
    private String precioUnitario;
    private String descuento;
    private String precioTotalSinImpuesto;

    private Impuestos impuestos;
}
