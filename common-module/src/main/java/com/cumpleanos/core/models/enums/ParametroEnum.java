package com.cumpleanos.core.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ParametroEnum {
    CXP_CIUDAD_PROVEEDORES("CXP", "UBI"),
    CXP_CATEGORIA_PROVEEDOR("CXP", "CPN"),
    CXP_POLITICA_PROVEEDOR("CXP", "POL"),
    COM_COA_TIPOCOM("COM", "COA"),

    CXC_CATEGORIA_CLIENTE("CXC", "CAT"),;

    private final String sigla;
    private final String secuencia;
}
