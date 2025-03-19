package com.cumpleanos.ecommerce.service.implementations;

import com.cumpleanos.ecommerce.configuration.WooCommerceProperties;
import com.cumpleanos.ecommerce.service.interfaces.WooCommerceService;
import com.icoderman.woocommerce.ApiVersionType;
import com.icoderman.woocommerce.EndpointBaseType;
import com.icoderman.woocommerce.WooCommerce;
import com.icoderman.woocommerce.WooCommerceAPI;
import com.icoderman.woocommerce.oauth.OAuthConfig;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("classpath:ecommerce.properties")
public class WooCommerceServiceImpl implements WooCommerceService {

    private WooCommerce wooCommerce;

    public WooCommerceServiceImpl(WooCommerceProperties properties){
        OAuthConfig config = new OAuthConfig(
                properties.getUrl(),
                properties.getClient(),
                properties.getSecretClient()
        );

        this.wooCommerce = new WooCommerceAPI(config, ApiVersionType.V3);
    }
    // Obtener una categoría por nombre
    @Override
    public Integer obtenerCategoriaId(String nombreCategoria) {
        Map<String,String> params = new HashMap<>();
        params.put("search", nombreCategoria);
        List<Map<String,Object>> categorias = wooCommerce.getAll(EndpointBaseType.PRODUCTS_CATEGORIES.getValue(), params);

        if (!categorias.isEmpty()){
            return (Integer) categorias.get(0).get("id");
        }
        return null;
    }

    // Crear una categoría si no existe
    @Override
    public Integer crearCategoria(String nombreCategoria, Integer categoriaPadre) {
        if (nombreCategoria == null || nombreCategoria.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría no puede estar vacío");
        }

        Map<String, Object> categoriaData = new HashMap<>();
        categoriaData.put("name", nombreCategoria);

        // Si la categoría tiene un padre, lo agregamos
        if (categoriaPadre != null) {
            categoriaData.put("parent", categoriaPadre);
        }

        // Enviar la categoría a WooCommerce
        Map<String, Object> categoriaCreada = wooCommerce.create(EndpointBaseType.PRODUCTS_CATEGORIES.getValue(), categoriaData);

        // Verificar si la API devolvió un ID correcto
        if (categoriaCreada != null && categoriaCreada.containsKey("id")) {
            return (Integer) categoriaCreada.get("id");
        } else {
            System.err.println("Error al crear la categoría: " + categoriaCreada);
            return null; // Manejo de error
        }
    }

    // Subir un producto con categoría y subcategoría
    @Override
    public Map<String, Object> subirProducto(String nombre, String precio, String descripcion, String categoria, String subcategoria) {
        // Verificar si la categoría existe, si no, crearla
        Integer categoriaId = obtenerCategoriaId(categoria);
        if (categoriaId == null) {
            categoriaId = crearCategoria(categoria, null);
        }

        // Verificar si la subcategoría existe, si no, crearla bajo la categoría padre
        Integer subcategoriaId = obtenerCategoriaId(subcategoria);
        if (subcategoriaId == null) {
            subcategoriaId = crearCategoria(subcategoria, categoriaId);
        }

        // Crear el producto con la subcategoría asignada
        Map<String, Object> productData = new HashMap<>();
        productData.put("name", nombre);
        productData.put("type", "simple");
        productData.put("regular_price", precio);
        productData.put("description", descripcion);

        //  Asegurar que categories[] contenga un Integer
        List<Map<String, Object>> categorias = new ArrayList<>();

        Map<String, Object> cat1 = new HashMap<>();
        cat1.put("id", Integer.parseInt(subcategoriaId.toString()));//Asegurar que sea Integer y no String
        categorias.add(cat1);

        productData.put("categories", categorias);

        return wooCommerce.create(EndpointBaseType.PRODUCTS.getValue(), productData);
    }

}
