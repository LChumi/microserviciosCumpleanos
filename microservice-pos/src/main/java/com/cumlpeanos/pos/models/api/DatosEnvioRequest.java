package com.cumlpeanos.pos.models.api;

import lombok.Data;

@Data
public class DatosEnvioRequest {
    private String numReferencia = "";
    private String tipoCredito;
    private int cuotas = 0;
    private double baseImponible;
    private double iva;
    private double servicio = 0.0;
    private double propina = 0.0;
    private double impuesto = 0.0;
    private String numTerminal = "";
    private double otros1 = 0.0;
    private double otros2 = 0.0;
    private double otros3 = 0.0;
    private double otros4 = 0.0;
    private double adicional1 = 0.0;
    private double adicional2 = 0.0;
    private double base0;
    private String fin = "";
}
