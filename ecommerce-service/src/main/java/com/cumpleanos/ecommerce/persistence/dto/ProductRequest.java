package com.cumpleanos.ecommerce.persistence.dto;

import java.util.Optional;

public record ProductRequest(
        String nombre,
        String sku,
        String precio,
        String descripcion,
        String categoria,
        String subcategoria,
        String stock,
        Boolean withIva
) {}
