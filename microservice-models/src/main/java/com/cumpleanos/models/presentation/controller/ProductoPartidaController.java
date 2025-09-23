package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.builders.ProductoPartidaBuilder;
import com.cumpleanos.models.service.interfaces.IProductoPartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductoPartidaController {

    private final IProductoPartidaService service;

    @GetMapping("producto-partida/get/{producto}/{empresa}")
    public ResponseEntity<ProductoPartidaBuilder> getByProductoAndEmpresa(@PathVariable Long producto, @PathVariable Long empresa) {

        ProductoPartidaBuilder builder = service.getPartidaBuilder(producto, empresa);
        return ResponseEntity.ok().body(builder);
    }
}
