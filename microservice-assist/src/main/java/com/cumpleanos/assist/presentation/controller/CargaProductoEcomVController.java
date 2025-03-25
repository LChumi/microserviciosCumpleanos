package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.service.interfaces.ICargaProductoEcomVService;
import com.cumpleanos.core.models.views.CargaProductoEcomV;
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
public class CargaProductoEcomVController {

    private final ICargaProductoEcomVService service;

    @GetMapping("/producto-ecom/{id}")
    public ResponseEntity<CargaProductoEcomV> getProducto(@PathVariable Long id) {
        CargaProductoEcomV p = service.findByProducto(id);
        return ResponseEntity.ok(p);
    }
}
