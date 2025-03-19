package com.cumpleanos.ecommerce.service.interfaces;

import java.util.Map;

public interface WooCommerceService {

    Integer obtenerCategoriaId(String nombreCategoria);

    Integer crearCategoria(String nombreCategoria, Integer idPadre);

    Map<String, Object> subirProducto(String nombre, String precio, String descripcion, String categoria, String subcategoria);
}
