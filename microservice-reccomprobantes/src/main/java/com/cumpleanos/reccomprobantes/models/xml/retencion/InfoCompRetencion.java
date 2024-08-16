package com.cumpleanos.reccomprobantes.models.xml.retencion;

import lombok.Data;

@Data
public class InfoCompRetencion {
    private String fechaEmision;
    private String dirEstablecimiento;
    private String contribuyenteEspecial;
    private String obligadoContabilidad;
    private String tipoIdentificacionSujetoRetenido;
    private String parteRel;
    private String razonSocialSujetoRetenido;
    private String identificacionSujetoRetenido;
    private String periodoFiscal;
}
