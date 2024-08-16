package com.cumpleanos.reccomprobantes.models.xml.factura;

import lombok.Data;

@Data
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
