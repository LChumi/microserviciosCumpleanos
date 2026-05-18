package com.cumpleanos.common.records;

public record RevisionProductoRequest(
        String barra,
        Long creposicion,
        Long empresa,
        String usuario,
        Long cantidad,
        boolean shouldAdd
) {
}
