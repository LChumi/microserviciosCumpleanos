package com.cumpleanos.reccomprobantes.models.xml.factura;

import lombok.Data;

import java.util.List;
@Data
public class Factura {
    private String id;
    private String version;

    private InfoTributaria infoTributaria;
    private InfoFactura infoFactura;
    private List<Detalle> detalles;
    private InfoAdicional infoAdicional;
}
