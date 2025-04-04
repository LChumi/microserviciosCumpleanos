package com.cumpleanos.ecommerce.utils;

import com.cumpleanos.ecommerce.persistence.dto.ProductRequest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WoocommerceUtils {

    public static Map<String, Object> convertObjectToMap(ProductRequest request, Integer imageId) {
        Map<String, Object> productData = new HashMap<>();
        productData.put("name", request.nombre());
        productData.put("sku", request.sku());
        productData.put("type", "simple");
        productData.put("regular_price", request.precio());
        productData.put("description", request.descripcion());
        productData.put("manage_stock", "true");
        productData.put("stock_quantity", request.stock());
        if (Boolean.FALSE.equals(request.withIva())) {
            productData.put("shipping_taxable", "false");
        }

        //Agregar referencia
        if (request.empresa() != null) {
            List<Map<String, Object>> metaData = new ArrayList<>();
            Map<String,Object> referencia = new HashMap<>();
            referencia.put("key", "empresa");
            referencia.put("value", request.empresa());
            metaData.add(referencia);
            productData.put("meta_data", metaData);
        }

        //Agregar Imagen
        if (imageId != null) {
            productData.put("images", List.of(Map.of("id", imageId)));
        }
        
        return productData;
    }

    public static String startOfDay(LocalDate date){
        return date.atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

    public static String endOfDay(LocalDate date){
        return date.atTime(23, 59, 59).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }
}
