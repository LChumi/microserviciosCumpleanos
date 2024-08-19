package com.cumpleanos.core.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoDoc {
    SOLCOMIMP(119,"SOLICITUD COMPRA IMPORTACIONES"),
    ORCCOMIMP(120, "ORDEN DE COMPRA IMPORTACIONES");

    private final int codigo;
    private final String descripcion;

    public static TipoDoc valueOf(int codigo) {
        for (TipoDoc t : TipoDoc.values()) {
            if (t.codigo == codigo) {
                return t;
            }
        }
        return null;
    }
}
