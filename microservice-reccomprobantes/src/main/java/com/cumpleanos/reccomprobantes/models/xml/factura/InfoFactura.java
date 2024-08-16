package com.cumpleanos.reccomprobantes.models.xml.factura;

import lombok.Data;

import java.util.List;

@Data
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

    private TotalConImpuestos totalConImpuestos;

    private String propina;
    private String importeTotal;
    private String moneda;

    private List<Pago> pagos;
}
