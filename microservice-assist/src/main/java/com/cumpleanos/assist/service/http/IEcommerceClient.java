package com.cumpleanos.assist.service.http;

import com.cumpleanos.assist.persistence.inmutables.ProductRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "ecommerce-service")
public interface IEcommerceClient {

    @PostMapping("/woocommerce/upload-product")
    ResponseEntity<Map<String, Object>> subirProducto(@RequestBody ProductRequest producto);

    @PutMapping("/woocommerce/products/{sku}")
    ResponseEntity<Map<String,Object>> updateProduct(@PathVariable String sku, ProductRequest request);
}
