package com.cumpleanos.ecommerce.service.interfaces;

import com.cumpleanos.common.builders.ecommerce.PedidoWoocommerce;
import com.cumpleanos.common.records.ProductEcomRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface WooCommerceService {

    List<Map<String, Object>> obtenerCategoriaId(String nombreCategoria);

    Integer obtenerProductoId(String sku);

    Integer crearCategoria(String nombreCategoria, Integer idPadre);

    Map<String, Object> subirProducto(ProductEcomRequest productRequest);

    Map<String, Object> actualizarProducto(String sku, Integer process, ProductEcomRequest request);

    List<Map<String, Object>> getOrders();

    List<PedidoWoocommerce> getOrdesrByDate(LocalDate fecha, LocalDate fechaFin);
}
