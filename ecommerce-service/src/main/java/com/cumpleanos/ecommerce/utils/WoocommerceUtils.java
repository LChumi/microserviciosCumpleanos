package com.cumpleanos.ecommerce.utils;

import com.cumpleanos.ecommerce.persistence.dto.ProductRequest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class WoocommerceUtils {

    public static Map<String, Object> convertObjectToMap(ProductRequest request) {
        Map<String, Object> productData = new HashMap<>();
        productData.put("name", encodedString(request.nombre()));
        productData.put("sku", request.sku());
        productData.put("type", "simple");
        productData.put("regular_price", request.precio());
        productData.put("description", encodedString(request.descripcion()));
        return productData;
    }

    public static String encodedString(String string) {
        return URLEncoder.encode(string, StandardCharsets.UTF_8);
    }
}
