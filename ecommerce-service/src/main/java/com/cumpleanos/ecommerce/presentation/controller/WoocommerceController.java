package com.cumpleanos.ecommerce.presentation.controller;

import com.cumpleanos.ecommerce.persistence.dto.ProductRequest;
import com.cumpleanos.ecommerce.service.implementations.WooCommerceMediaServiceImpl;
import com.cumpleanos.ecommerce.service.interfaces.WooCommerceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ecommerce")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class WoocommerceController {

    private final WooCommerceService service;
    private final WooCommerceMediaServiceImpl mediaService;

    @PostMapping("/woocommerce/upload-product")
    public ResponseEntity<Map<String, Object>> subirProducto(@RequestBody ProductRequest producto) {
        Map<String, Object> result = service.subirProducto(producto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/woocommerce/products/{sku}/{process}")
    public ResponseEntity<Map<String,Object>> updateProduct(@PathVariable String sku, @PathVariable Integer process, @RequestBody ProductRequest request) {
        Map<String, Object> result = service.actualizarProducto(sku,process, request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/woocommerce/orders-date/{d1}/{d2}")
    public ResponseEntity<List<Map<String, Object>>> getOrdersDate(@PathVariable LocalDate d1, @PathVariable LocalDate d2) {
        List<Map<String,Object>> listOrders = service.getOrdesrByDate(d1, d2);
        return ResponseEntity.ok(listOrders);
    }

    @GetMapping("/wordpress/images/{sku}")
    public ResponseEntity<Integer> uploadImage(@PathVariable String sku) throws IOException {
        Integer response = mediaService.uploadImage(sku);
        return ResponseEntity.ok(response);
    }
}
