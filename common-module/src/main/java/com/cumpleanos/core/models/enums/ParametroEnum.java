package com.cumpleanos.core.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ParametroEnum {
    //PROVEEDORES
    CXP_CIUDAD_PROVEEDORES("CXP", "UBI"),
    CXP_CATEGORIA_PROVEEDOR("CXP", "CPN"),
    CXP_POLITICA_PROVEEDOR("CXP", "POL"),
    COM_COA_TIPOCOM("COM", "COA"),

    //CLIENTE
    CXC_CATEGORIA_CLIENTE("CXC", "CAT"),
    CXC_POLITICA_CLIENTE("CXC", "POL"),
    CXC_CIUDADES_CLIENTES("CXC", "UBI"),
    CXC_LISTAPRE_CLIENTES("CXC", "LPR"),

    //CLIENTE ECOMMERCE
    CXC_AGENTE_ECOMMERCE_CLIENTES("CXC", "AGW"),
    CXC_TIPOCLI_ECOOMERCE_CLIENTES("CXC", "TCE"),
    ;

    private final String sigla;
    private final String secuencia;
}
