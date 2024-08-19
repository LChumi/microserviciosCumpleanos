package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Detalle {
    private String codigoInterno;
    private String codigoAdicional;
    private String descripcion;
    private String cantidad;
    private String precioUnitario;
    private String descuento;
    private String precioTotalSinImpuesto;
    @XmlElement(name = "impuestos")
    private Impuestos impuestos;
}
