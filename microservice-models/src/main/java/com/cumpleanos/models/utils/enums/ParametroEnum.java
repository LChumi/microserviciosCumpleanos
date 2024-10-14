package com.cumpleanos.models.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ParametroEnum {
    CXP_CIUDAD("CXP", "UBI"),
    CXP_CATEGORIA("CXP", "CPN"),
    CXP_POLITICA("CXP", "POL"),
    COM_COA_TIPOCOM("COM", "COA");

    private final String sigla;
    private final String secuencia;
}
