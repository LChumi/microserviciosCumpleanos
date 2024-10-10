package com.cumpleanos.reccomprobantes.persistence.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ParametCoaEnum {
    COA("COM", "COA", "CODIGO DE TABLA COA, EL CUAL VA A VALIDAR LOS TIPOS DE COMPROBANTE AL REGISTRAR EL DETALLE DE GASTOS", 3),
    POL("COM", "SEC", "CODIGO DE LA TABLA DEL COA QUE TIENE LA SECUENCIA DE TRANSACCION", 2),
    CRE("COM", "CRE", "CODIGO DE LA TABLA DEL COA QUE TIENE EL CREDITO TRIBUTARIO", 4),
    DEV("COM","DEV", "CODIGO DE LA TABLA DEL COA QUE TIENE EL DERECHO A DEVOLUCION", 9);

    private final String sigla;
    private final String secuencia;
    private final String nombre;
    private final int valor;

    public static ParametCoaEnum getBySecuencia(String secuencia) {
        for (ParametCoaEnum parametro : ParametCoaEnum.values()) {
            if (parametro.getSecuencia().equals(secuencia)) {
                return parametro;
            }
        }
        return null;
    }
}