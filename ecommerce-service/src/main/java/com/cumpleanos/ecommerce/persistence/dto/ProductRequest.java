package com.cumpleanos.ecommerce.persistence.dto;

public record ProductRequest(
        String nombre,
        String sku,
        String precio,
        String descripcion,
        String categoria,
        String subcategoria
) {}
