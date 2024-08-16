package com.cumpleanos.reccomprobantes.models.xml.factura;

import lombok.Data;

import java.util.List;

@Data
public class Detalles {
    private List<Detalle> detalle;
}
