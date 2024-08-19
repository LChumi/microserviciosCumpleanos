package com.cumpleanos.reccomprobantes.models.xml.retencion;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class InfoTributaria {
    private String ambiente;
    private String tipoEmision;
    private String razonSocial;
    private String ruc;
    private String claveAcceso;
    private String codDoc;
    private String estab;
    private String ptoEmi;
    private String secuencial;
    private String dirMatriz;

    public String noComprobante(){
        return estab+"-"+ptoEmi+"-"+secuencial;
    }
}
