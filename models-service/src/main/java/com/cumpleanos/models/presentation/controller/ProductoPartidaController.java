package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.builders.ProductoPartidaBuilder;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.ProductoPartida;
import com.cumpleanos.models.service.interfaces.IProductoPartidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Tag(name = "Producto Partida", description = "Documentacion API de producto-partida")
public class ProductoPartidaController {

    private final IProductoPartidaService service;

    @Operation(summary = "Get producto-partida", description = "Obtiene la partida de un producto")
    @Parameters({
            @Parameter(name = "producto", description = "Codigo de Producto"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/producto-partida/get/{producto}/{empresa}/default")
    public ResponseEntity<ProductoPartidaBuilder> getByProductoAndEmpresa(@PathVariable Long producto, @PathVariable Long empresa) {

        ProductoPartidaBuilder builder = service.getPartidaBuilder(producto, empresa);
        return ResponseEntity.ok().body(builder);
    }

    @Operation(summary = "Crear", description = "Crea una nueva Partida")
    @PostMapping("/producto-partida/save")
    public ResponseEntity<ProductoPartida> save(@RequestBody ProductoPartida partida) {
        ProductoPartida save = service.save(partida);
        return ResponseEntity.ok().body(save);
    }

    @Operation(summary = "Actualizar partida", description = "Actualiza la partida de un producto y la deja como definida al resta como inactiva")
    @Parameters({
            @Parameter(name = "producto", description = "Codigo de Producto"),
            @Parameter(name = "partida", description = "Codigo de partida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/prodcuto-partida/update-default/{producto}/{partida}/{empresa}")
    public ResponseEntity<ServiceResponse> updatePartidaDefault(@PathVariable Long producto, @PathVariable Long partida, @PathVariable Long empresa) {
        ServiceResponse serviceResponse = service.updatePartidaDefault(producto, partida, empresa);
        return ResponseEntity.ok().body(serviceResponse);
    }
}
