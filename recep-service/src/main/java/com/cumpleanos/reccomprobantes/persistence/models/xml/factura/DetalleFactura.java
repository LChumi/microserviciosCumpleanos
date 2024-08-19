package com.cumpleanos.reccomprobantes.persistence.models.xml.factura;

import com.cumpleanos.reccomprobantes.persistence.models.xml.Impuestos;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "detalle")
@XmlAccessorType(XmlAccessType.FIELD)
public class DetalleFactura {
    private String codigoPrincipal;
    private String codigoAuxiliar;
    private String descripcion;
    private String cantidad;
    private String precioUnitario;
    private String descuento;
    private String precioTotalSinImpuesto;
    @XmlElement(name = "impuestos")
    private Impuestos impuestos;
}
