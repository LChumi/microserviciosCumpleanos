package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import lombok.Data;

@Data
public class InfoNotaCredito {
    private String fechaEmision;
    private String dirEstablecimiento;
    private String tipoIdentificacionComprador;
    private String razonSocialComprador;
    private String identificacionComprador;
    private String contribuyenteEspecial;
    private String obligadoContabilidad;
    private String codDocModificado;
    private String numDocModificado;
    private String fechaEmisionDocSustento;
    private String totalSinImpuestos;
    private String valorModificacion;
    private String moneda;

    private TotalConImpuestos totalConImpuestos;

    private String motivo;
}
