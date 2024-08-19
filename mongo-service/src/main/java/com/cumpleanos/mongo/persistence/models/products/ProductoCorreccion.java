package com.cumpleanos.mongo.persistence.models.products;

import lombok.Data;

@Data
public class ProductoCorreccion {
    private String usuario;
    private String fecha;
    private String detalle;
}