package com.cumpleanos.mongo.persistence.models.products.request;

import com.cumpleanos.mongo.persistence.models.products.ProductoCorreccion;

public record CorreccionRequest(
        String idProducto,
        ProductoCorreccion correccion
) {
}
