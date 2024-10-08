package com.cumpleanos.reccomprobantes.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ParametProveedorEnum {
    PROV("COM", "PRV", "CUENTA CONTABLE PROVICIONES DE VALORACIONES", 0),
    POL("CXP", "POL", "POLITICA DE PROVEEDORES", 10002552),
    CPN("CXP", "CPN", "CODIGO DE LA CATEGORIA DE LOS PROVEEDORES NACIONALES", 10000251),
    CPR("CXP","CPR", "CODIGO DE LA CATEGORIA PROVEEDOR DEL EXTERIOR", 10000252);

    private final String sigla;
    private final String secuencia;
    private final String nombre;
    private final int valor;

    public static ParametProveedorEnum getBySecuencia(String secuencia) {
        for (ParametProveedorEnum parametro : ParametProveedorEnum.values()) {
            if (parametro.getSecuencia().equals(secuencia)) {
                return parametro;
            }
        }
        return null;
    }
}