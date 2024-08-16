package com.cumpleanos.reccomprobantes.models.xml.retencion;

import lombok.Data;

@Data
public class Retencion {
    private String codigo;
    private String codigoRetencion;
    private String baseImponible;
    private String porcentajeRetener;
    private String valorRetenido;
    private String codDocSustento;
    private String numDocSustento;
    private String fechaEmisionDocSustento;
    private String periodoFiscal;
}
