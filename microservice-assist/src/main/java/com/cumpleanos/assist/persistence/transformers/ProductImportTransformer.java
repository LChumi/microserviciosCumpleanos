package com.cumpleanos.assist.persistence.transformers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductImportTransformer {
    private String id;
    private String item;
    private String nombre;
    private int cantidad;
    private double fob;
    private double cbm;
    private String status;
    private String imagen;
    @Setter(AccessLevel.NONE)
    private int cantidadTrancito=0;
    private Set<ImpProdTrancitoTransformer> trancitos;

    public void calcularCantidadEnTrancito() { cantidadTrancito = 0; // Inicializar a 0 antes de sumar
        if (!trancitos.isEmpty()) {
            for (ImpProdTrancitoTransformer trancito : trancitos) {
                cantidadTrancito += trancito.getCantidadPedida();
            }
        }
    }
}