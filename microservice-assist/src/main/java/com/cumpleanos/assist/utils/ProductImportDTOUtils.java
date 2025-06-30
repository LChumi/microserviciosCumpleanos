package com.cumpleanos.assist.utils;

import com.cumpleanos.assist.persistence.transformers.ImpProdTrancitoTransformer;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductImportDTOUtils {

    public static Set<ImpProdTrancitoTransformer> chekImports(Set<ImpProdTrancitoVw> items) {
        return items.stream()
                .map(ImpProdTrancitoTransformer::mapToImpProdTrancitoVw)
                .collect(Collectors.toSet());
    }

    public static void calcularTotales(ProductImportTransformer item) {
        item.calcularCantidadTotal();
        item.calcularCbmTotal();
        item.calcularFobTotal();
    }
}
