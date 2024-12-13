package com.cumpleanos.assist.persistence.transformers;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

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
    private Long proveedor;
    private double cbm;
    private String status;
    private String imagen;
    private Set<ImpProdTrancitoTransformer> trancitos;
}