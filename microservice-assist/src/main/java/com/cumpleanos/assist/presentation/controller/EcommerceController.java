package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.service.interfaces.ecommerce.IProductosEcommerceService;
import com.cumpleanos.assist.service.interfaces.ecommerce.IStockEcommerceVService;
import com.cumpleanos.core.models.dto.ServiceResponse;
import com.cumpleanos.core.models.views.StockEcommerceV;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EcommerceController {

    private final IProductosEcommerceService serviceProductsEcommerce;
    private final IStockEcommerceVService serviceStockEcommerce;

    @GetMapping("/ecommerce/producto-save/{id}/{empresa}")
    public ResponseEntity<ServiceResponse> getProducto(@PathVariable Long id, @PathVariable Long empresa) {
        ServiceResponse p = serviceProductsEcommerce.uploadProductEcommerce(id, empresa);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/ecommerce/product-update/{id}/{empresa}/{sku}")
    public ResponseEntity<ServiceResponse> getProductoUpdate(@PathVariable Long id, @PathVariable Long empresa, @PathVariable String sku) {
        ServiceResponse response = serviceProductsEcommerce.updateProductEcommerce(id,sku,empresa);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/stock-ecommerce/view")
    public ResponseEntity<List<StockEcommerceV>> findAll() {
        List<StockEcommerceV> lista = serviceStockEcommerce.findAll();
        log.info(lista.toString());
        return ResponseEntity.ok(lista);
    }
}
