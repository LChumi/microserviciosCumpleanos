package com.cumpleanos.reccomprobantes.persistence.models.xml.factura;

import com.cumpleanos.reccomprobantes.persistence.models.xml.TotalConImpuestos;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class InfoFactura {
    private String fechaEmision;
    private String dirEstablecimiento;
    private String contribuyenteEspecial;
    private String obligadoContabilidad;
    private String tipoIdentificacionComprador;
    private String razonSocialComprador;
    private String identificacionComprador;
    private String totalSinImpuestos;
    private String totalDescuento;
    @XmlElement(name = "totalConImpuestos")
    private TotalConImpuestos totalConImpuestos;
    private String propina;
    private String importeTotal;
    private String moneda;
    @XmlElementWrapper(name = "pagos")
    @XmlElement(name = "pago")
    private List<PagoFactura> pagos;
}
