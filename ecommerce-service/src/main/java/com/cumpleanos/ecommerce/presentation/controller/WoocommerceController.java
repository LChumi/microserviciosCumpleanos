package com.cumpleanos.ecommerce.presentation.controller;

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
    public ResponseEntity<Map<String, Object>> subirProducto(@RequestBody Map<String, String> producto) {
        Map<String, Object> result = service.subirProducto(
                producto.get("nombre"),
                producto.get("precio"),
                producto.get("descripcion"),
                producto.get("categoria"),
                producto.get("subcategoria")
        );
        return ResponseEntity.ok(result);
    }
}
