package com.cumpleanos.common.records;

import java.math.BigDecimal;

public record StockEcommerceVDTO(
        String codigo,
        Long empresa,
        Long producto,
        String proId,
        String proId1,
        String proNombre,
        Long seccion,
        BigDecimal precio1,
        Long empresaGrupo,
        String urlWs,
        Long stock,
        Long cantMin,
        String tipo
) {}
