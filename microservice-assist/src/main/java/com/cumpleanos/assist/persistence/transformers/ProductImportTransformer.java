package com.cumpleanos.assist.persistence.transformers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductImportTransformer {
    private int secuencia;
    private String id;
    private String item;
    private String nombre;
    private int cantidad;
    private double fob;
    private double cbm;
    private int cxb;
    private String status;
    private String codFabrica;
    private String novedad;

    @Setter(AccessLevel.NONE)
    private int cantidadTotal;

    @Setter(AccessLevel.NONE)
    private double cbmTotal;

    @Setter(AccessLevel.NONE)
    private double fobTotal;

    @Setter(AccessLevel.NONE)
    private int cantidadTrancito;

    private Set<ImpProdTrancitoTransformer> trancitos;

    /**
     * Calcula la cantidad total en tránsito sumando las cantidades pedidas de cada objeto en la lista `trancitos`.
     */
    public void calcularCantidadEnTrancito() {
        cantidadTrancito = 0; // Inicializar a 0 antes de sumar
        if (trancitos != null && !trancitos.isEmpty()) {
            for (ImpProdTrancitoTransformer trancito : trancitos) {
                cantidadTrancito += trancito.getCantidadPedida();
            }
        }
    }

    public void calcularCantidadTotal() {
        cantidadTotal = cantidad * cxb;
    }

    public void calcularCbmTotal() {
        cbmTotal = cbm * cantidad;
    }

    public void calcularFobTotal() {
        fobTotal = fob * cantidadTotal;
    }
}