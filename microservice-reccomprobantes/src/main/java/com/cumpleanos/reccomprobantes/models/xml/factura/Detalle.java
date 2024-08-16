package com.cumpleanos.reccomprobantes.models.xml.factura;

import lombok.Data;

@Data
public class Detalle {
    private String codigoPrincipal;
    private String codigoAuxiliar;
    private String descripcion;
    private String cantidad;
    private String precioUnitario;
    private String descuento;
    private String precioTotalSinImpuesto;

    private Impuestos impuestos;
}
