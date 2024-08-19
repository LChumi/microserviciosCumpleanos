package com.cumpleanos.reccomprobantes.persistence.models.xml.notaDebito;

import com.cumpleanos.reccomprobantes.persistence.models.xml.Impuestos;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

import java.util.Date;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class InfoNotaDebito {
    public String fechaEmision;
    public String dirEstablecimiento;
    public String tipoIdentificacionComprador;
    public String razonSocialComprador;
    public String identificacionComprador;
    public String contribuyenteEspecial;
    public String obligadoContabilidad;
    public String codDocModificado;
    public String numDocModificado;
    public String fechaEmisionDocSustento;
    public String totalSinImpuestos;
    public Impuestos impuestos;
    public String valorTotal;
}
