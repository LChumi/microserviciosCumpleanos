package com.cumpleanos.ecommerce.presentation.controller;

import com.cumpleanos.ecommerce.persistence.dto.ProductRequest;
import com.cumpleanos.ecommerce.service.interfaces.WooCommerceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("ecommerce")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class WoocommerceController {

    private final WooCommerceService service;

    @PostMapping("/woocommerce/upload-product")
    public ResponseEntity<Map<String, Object>> subirProducto(@RequestBody ProductRequest producto) {
        Map<String, Object> result = service.subirProducto(producto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/woocommerce/products/{id}")
    public ResponseEntity<Map<String,Object>> updateProduct(@PathVariable Integer id, ProductRequest request){
        Map<String, Object> result = service.actualizarProducto(id, request);
        return ResponseEntity.ok(result);
    }
}
