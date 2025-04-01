package com.cumpleanos.ecommerce.presentation.controller;

import com.cumpleanos.ecommerce.persistence.dto.ProductRequest;
import com.cumpleanos.ecommerce.service.interfaces.WooCommerceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
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

    @PutMapping("/woocommerce/products/{sku}")
    public ResponseEntity<Map<String,Object>> updateProduct(@PathVariable String sku, ProductRequest request){
        Map<String, Object> result = service.actualizarProducto(sku, request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/woocommerce/orders-date/{d1}/{d2}")
    public ResponseEntity<List<Map<String, Object>>> getOrdersDate(@PathVariable LocalDate d1, @PathVariable LocalDate d2) {
        List<Map<String,Object>> listOrders = service.getOrdesrByDate(d1, d2);
        return ResponseEntity.ok(listOrders);
    }

}
