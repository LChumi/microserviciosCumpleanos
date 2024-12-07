package com.cumpleanos.assist.persistence.transformers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImportTransformer {
    private String id;
    private String item;
    private String nombre;
    private int cantidad;
    private double fob;
    private Long proveedor;
    private String status;
    private String imagen;
}