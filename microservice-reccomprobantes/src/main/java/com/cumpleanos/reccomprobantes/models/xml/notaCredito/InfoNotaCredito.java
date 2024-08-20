package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import com.cumpleanos.reccomprobantes.models.xml.TotalConImpuestos;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
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
    @XmlElement(name = "totalConImpuestos")
    private TotalConImpuestos totalConImpuestos;
    private String motivo;
}
