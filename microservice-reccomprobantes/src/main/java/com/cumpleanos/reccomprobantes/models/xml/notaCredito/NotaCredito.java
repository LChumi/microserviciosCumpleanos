package com.cumpleanos.reccomprobantes.models.xml.notaCredito;

import lombok.Data;

@Data
public class NotaCredito {
    private String id;
    private String version;

    private InfoTributaria infoTributaria;
    private InfoNotaCredito infoNotaCredito;
    private Detalles detalles;
    private InfoAdicional infoAdicional;
}
