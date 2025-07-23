package com.cumpleanos.assist.utils;

import com.cumpleanos.assist.persistence.transformers.ImpProdTrancitoTransformer;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.persistence.views.ImpProdTrancitoVw;

import java.util.Set;
import java.util.stream.Collectors;

public class ProductImportDTOUtils {

    /**
     * Mapea un conjunto de entidades {@code ImpProdTrancitoVw} a sus correspondientes DTOs
     * mediante el método {@code mapToImpProdTrancitoVw}.
     *
     * @param items conjunto de entidades provenientes de la base de datos
     * @return conjunto de objetos {@code ImpProdTrancitoTransformer} convertidos
     */
    public static Set<ImpProdTrancitoTransformer> chekImports(Set<ImpProdTrancitoVw> items) {
        return items.stream()
                .map(ImpProdTrancitoTransformer::mapToImpProdTrancitoVw)
                .collect(Collectors.toSet());
    }


    /**
     * Funcion que calula los totales del intem
     */
    public static void calcularTotales(ProductImportTransformer item) {
        item.calcularCantidadTotal();
        item.calcularCbmTotal();
        item.calcularFobTotal();
    }
}
