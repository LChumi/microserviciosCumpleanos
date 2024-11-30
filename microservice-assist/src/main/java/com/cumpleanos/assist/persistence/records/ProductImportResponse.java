package com.cumpleanos.assist.persistence.records;

public record ProductImportResponse(
        String id,
        String item,
        String nombre,
        int cantidad,
        double fob,
        Long proveedor
) {}