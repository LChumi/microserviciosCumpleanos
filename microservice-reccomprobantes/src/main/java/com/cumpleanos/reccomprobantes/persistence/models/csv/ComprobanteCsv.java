package com.cumpleanos.reccomprobantes.persistence.models.csv;

import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import lombok.Data;

@Data
public class ComprobanteCsv implements Comprobante {
    private String rucEmisor;
    private String razonSocialEmisor;
    private String tipoComprobante;
    private String serieComprobante;
    private String claveAcceso;
    private String fechaAutorizacion;
    private String fechaEmision;
    private String identificacionReceptor;
    private String valorSinImpuestos;
    private String iva;
    private String importeTotal;
    private String numeroDocumentoModificado;
}
