package com.cumlpeanos.pos.models.api;

import lombok.Data;

@Data
public class DatosEnvioRequest {
    private String numReferencia = "";
    private String tipoCredito;
    private int cuotas;
    private double baseImponible;
    private double iva;
    private double servicio;
    private double propina;
    private double impuesto;
    private String numTerminal;
    private double otros1;
    private double otros2;
    private double otros3;
    private double otros4;
    private double adicional1;
    private double adicional2;
    private double base0;
    private String fin;
    public static int[] longitudes;
    public static String[] dTipoCredito;
}
