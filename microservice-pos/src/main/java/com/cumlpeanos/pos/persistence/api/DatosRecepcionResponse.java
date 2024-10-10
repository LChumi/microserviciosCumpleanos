package com.cumlpeanos.pos.persistence.api;

import lombok.Data;

@Data
public class DatosRecepcionResponse {
    private String campo84Fijo;
    private boolean result;
    private Object errorMessage;
    private String codigoTerminal;
    private String mensajeResultado;
    private String numeroAprobacion;
    private String codigoAdquiriente;
    private String nombreAdquiriente;
    private String lote;
    private String codigoResultado;
    private String tarjetaHabiente;
    private String nombreEmisor;
    private String referencia;
    private String numeroTarjeta;
    private String hora;
    private String p57;
    private String fijo;
    private String codigoComercio;
    private String fecha;
}
