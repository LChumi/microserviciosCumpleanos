package com.cumpleanos.ecommerce.utils;

import com.cumpleanos.ecommerce.persistence.dto.ProductRequest;

import java.util.HashMap;
import java.util.Map;

public class WoocommerceUtils {

    public static Map<String, Object> convertObjectToMap(ProductRequest request) {
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
        return productData;
    }
}
