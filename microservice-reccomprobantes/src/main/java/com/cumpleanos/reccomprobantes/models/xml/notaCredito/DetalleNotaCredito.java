package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import com.cumpleanos.reccomprobantes.models.xml.Impuestos;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "detalle")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetalleNotaCredito {
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
