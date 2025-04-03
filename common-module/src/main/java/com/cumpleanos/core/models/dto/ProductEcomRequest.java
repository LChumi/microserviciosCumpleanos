package com.cumpleanos.core.models.dto;

public record ProductEcomRequest(
        String nombre,
        String sku,
        String precio,
        String descripcion,
        String categoria,
        String subcategoria,
        Integer stock,
        Integer empresa,
        Boolean withIva
) {}