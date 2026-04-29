package com.cumpleanos.pos.persistence.api.medianet;

import lombok.Data;

@Data
public class DatosEnvioPP {
    private double subtotal;
    private double subtotal0;
    private double iva;
    private double total;
    private String tipoTransaccion;
    private String codigoDiferido;
    private String plazo;
    private String mid;
    private String tid;
    private String cid;
    private String pVenta;
    private String hora;
    //Campos anulacion
    private String referencia;
    private String lote;
    private String numeroAutorizacion;
}
