package com.cumpleanos.ecommerce.service.implementations;

import com.cumpleanos.ecommerce.service.interfaces.WooCommerceService;
import com.icoderman.woocommerce.ApiVersionType;
import com.icoderman.woocommerce.EndpointBaseType;
import com.icoderman.woocommerce.WooCommerce;
import com.icoderman.woocommerce.WooCommerceAPI;
import com.icoderman.woocommerce.oauth.OAuthConfig;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${woocommerce.url}")
    private String url;
    @Value("${woocommerce.client}")
    private String client;
    @Value("${woocommerce.secret-client}")
    private String secretClient;

    public WooCommerceServiceImpl(){
        OAuthConfig config = new OAuthConfig(
                url,
                client,
                secretClient
        );

        this.wooCommerce = new WooCommerceAPI(config, ApiVersionType.V3);
    }
    // Obtener una categoría por nombre
    @Override
    public Integer obtenerCategoria(String nombreCategoria) {
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
    public Integer crearCategoria(String nombreCategoria, Integer idPadre) {
        Map<String, Object> categoriaData = new HashMap<>();
        categoriaData.put("id", nombreCategoria);
        if (idPadre != null){
            categoriaData.put("parent", idPadre);
        }

        Map<String, Object> categoriaCreada = wooCommerce.create(EndpointBaseType.PRODUCTS_CATEGORIES.getValue(), categoriaData);
        return (Integer) categoriaCreada.get("id");
    }

    // Subir un producto con categoría y subcategoría
    @Override
    public Map<String, Object> subirProducto(String nombre, String precio, String descripcion, String categoria, String subcategoria) {
        // Verificar si la categoria existe , Si no existe Crearla
        Integer categoriaId = obtenerCategoria(categoria);
        if (categoriaId == null){
            categoriaId = crearCategoria(categoria, null);
        }

        //Verificar la subcategoria si no existe crearla jusnto con la categoria padre
        Integer subcategoriaId = obtenerCategoria(subcategoria);
        if (subcategoriaId == null){
            subcategoriaId = crearCategoria(subcategoria, categoriaId);
        }

        //Crear el producro con la subcategoria asignada
        Map<String, Object> productData = new HashMap<>();
        productData.put("name", nombre);
        productData.put("type", "simple");
        productData.put("regular_price", precio);
        productData.put("description", descripcion);

        //Asociar a la subcategoria
        List<Map<String, Object>> categorias = new ArrayList<>();
        Map<String, Object> cat1 = new HashMap<>();
        cat1.put("id", subcategoriaId);
        categorias.add(cat1);
        productData.put("categories", categorias);

        return wooCommerce.create(EndpointBaseType.PRODUCTS.getValue(), productData);
    }
}
