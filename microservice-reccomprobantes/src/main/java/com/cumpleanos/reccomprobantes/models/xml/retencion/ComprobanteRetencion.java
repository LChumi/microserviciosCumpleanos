package com.cumpleanos.reccomprobantes.models.xml.retencion;

import lombok.Data;

@Data
public class ComprobanteRetencion {
    private String id;
    private String version;
    private InfoTributaria infoTributaria;
    private InfoCompRetencion infoCompRetencion;
    private DocsSustento docsSustento;
    private InfoAdicional infoAdicional;
}
