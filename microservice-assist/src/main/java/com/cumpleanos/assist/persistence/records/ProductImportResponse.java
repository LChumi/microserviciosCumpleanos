package com.cumpleanos.assist.persistence.records;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductImportResponse {
    private String id;
    private String item;
    private String nombre;
    private int cantidad;
    private double fob;
    private Long proveedor;
    private String status;
}