package com.cumpleanos.reccomprobantes.models.xml.factura;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "infoFactura")
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
    @JacksonXmlProperty(localName = "totalConImpuestos")
    private TotalConImpuestos totalConImpuestos;
    private String propina;
    private String importeTotal;
    private String moneda;
    @JacksonXmlElementWrapper(localName = "pagos")
    @JacksonXmlProperty(localName = "pago")
    private List<Pago> pagos;
}
