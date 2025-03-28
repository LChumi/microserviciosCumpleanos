package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.inmutables.ServiceResponse;
import com.cumpleanos.assist.service.interfaces.ecommerce.IProductosEcommerceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CargaProductoEcommerceController {

    private final IProductosEcommerceService service;

    @GetMapping("/ecommerce/producto-save/{id}/{empresa}")
    public ResponseEntity<ServiceResponse> getProducto(@PathVariable Long id, @PathVariable Long empresa) {
        ServiceResponse p = service.uploadProductEcommerce(id, empresa);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/eccomerce/product-update/{id}/{empresa}/{sku}")
    public ResponseEntity<ServiceResponse> getProductoUpdate(@PathVariable Long id, @PathVariable Long empresa, @PathVariable String sku) {
        ServiceResponse response = service.updateProductEcommerce(id,sku,empresa);
        return ResponseEntity.ok(response);
    }
}
