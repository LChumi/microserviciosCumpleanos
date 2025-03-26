package com.cumpleanos.assist.persistence.inmutables;

public record ProductRequest(
        String nombre,
        String sku,
        String precio,
        String descripcion,
        String categoria,
        String subcategoria,
        Integer stock,
        Boolean withIva
) {}
