package com.cumpleanos.assist.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public record DiscountObs(BigDecimal valDesc, BigDecimal porcDesc, String observacion) {
    public static DiscountObs of(List<Map<String, Object>> metaMap) {
        BigDecimal valDesc = BigDecimal.ZERO;
        BigDecimal porcDesc = BigDecimal.ZERO;
        String observacion = null;

        if (metaMap != null) {
            for (Map<String, Object> meta : metaMap) {
                String key = Objects.toString(meta.get("key"), "");
                Object value = meta.get("value");

                /* --Descuento -- */
                if ("_discount".equalsIgnoreCase(key) || "discount".equalsIgnoreCase(key)) {
                    if (value instanceof Map<?, ?> d) {
                        @SuppressWarnings("unchecked")
                        Map<String, Object> discountMap = (Map<String, Object>) d;
                        Object amountObj = discountMap.getOrDefault("amount", "0");
                        Object percentageObj = discountMap.getOrDefault("percentage", "0");

                        valDesc = new BigDecimal(amountObj.toString());
                        porcDesc = new BigDecimal(percentageObj.toString());
                    }
                }

                /* ---Observacion varios aliases ---*/
                if (List.of("observacion", "observation", "nota", "comment", "note", "comentario")
                        .contains(key.toLowerCase())) {
                    observacion = Objects.toString(value, "");
                }
            }
        }
        return new DiscountObs(valDesc, porcDesc, observacion);
    }

    /* Helper para saber si se encontro descuento */
    public boolean hasDiscount() {
        return valDesc.compareTo(BigDecimal.ZERO) != 0;
    }
}
