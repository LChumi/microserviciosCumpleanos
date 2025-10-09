package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.builders.ProductoPartidaBuilder;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.ProductoPartida;
import com.cumpleanos.models.service.interfaces.IProductoPartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductoPartidaController {

    private final IProductoPartidaService service;

    @GetMapping("/producto-partida/get/{producto}/{empresa}/default")
    public ResponseEntity<ProductoPartidaBuilder> getByProductoAndEmpresa(@PathVariable Long producto, @PathVariable Long empresa) {

        ProductoPartidaBuilder builder = service.getPartidaBuilder(producto, empresa);
        return ResponseEntity.ok().body(builder);
    }

    @PostMapping("/producto-partida/save")
    public ResponseEntity<ProductoPartida> save(@RequestBody ProductoPartida partida) {
        ProductoPartida save = service.save(partida);
        return ResponseEntity.ok().body(save);
    }

    @GetMapping("/prodcuto-partida/update-default/{producto}/{partida}/{empresa}")
    public ResponseEntity<ServiceResponse> updatePartidaDefault(@PathVariable Long producto, @PathVariable Long partida, @PathVariable Long empresa) {
        ServiceResponse serviceResponse = service.updatePartidaDefault(producto, partida, empresa);
        return ResponseEntity.ok().body(serviceResponse);
    }
}
