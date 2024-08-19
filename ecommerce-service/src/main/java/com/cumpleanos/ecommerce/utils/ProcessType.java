package com.cumpleanos.ecommerce.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProcessType {
    UPDATE_PRODUCT_ONLY(0, "Actualización de producto sin imagen"),
    UPLOAD_IMAGE(1, "Actualización de producto y carga de imagen");

    private final Integer process;
    private final String description;

    public static ProcessType getByProcess(Integer process) {
        for (ProcessType p : ProcessType.values()) {
            if (p.getProcess().equals(process)) {
                return p;
            }
        }
        return null;
    }
}