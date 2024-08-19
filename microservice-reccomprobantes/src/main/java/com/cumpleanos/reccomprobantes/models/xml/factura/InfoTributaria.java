package com.cumpleanos.reccomprobantes.models.xml.factura;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "infoTributaria")
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
    private String agenteRetencion;

    public String noComprobante(){
        return estab+"-"+ptoEmi+"-"+secuencial;
    }

}
