package com.cumpleanos.ecommerce.service.interfaces;

import com.cumpleanos.ecommerce.persistence.dto.ProductRequest;

import java.util.List;
import java.util.Map;

public interface WooCommerceService {

    Integer obtenerCategoriaId(String nombreCategoria);

    Integer obtenerProductoId(String sku);

    Integer crearCategoria(String nombreCategoria, Integer idPadre);

    Map<String, Object> subirProducto(ProductRequest productRequest);

    Map<String, Object> actualizarProducto(String sku, ProductRequest request);

    List<Map<String, Object>> getOrders();
}
