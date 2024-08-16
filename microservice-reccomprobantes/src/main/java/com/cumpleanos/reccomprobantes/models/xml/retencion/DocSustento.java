package com.cumpleanos.reccomprobantes.models.xml.retencion;

import lombok.Data;

@Data
public class DocSustento {
    private String codSustento;
    private String codDocSustento;
    private String numDocSustento;
    private String fechaEmisionDocSustento;
    private String pagoLocExt;
    private String totalSinImpuestos;
    private String importeTotal;

    private ImpuestosDocSustento impuestosDocSustento;
    private Retenciones retenciones;
    private Pagos pagos;
}
