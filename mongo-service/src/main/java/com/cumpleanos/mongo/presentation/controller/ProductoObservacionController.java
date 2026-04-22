package com.cumpleanos.mongo.presentation.controller;

import com.cumpleanos.mongo.persistence.models.products.ProductoObservacion;
import com.cumpleanos.mongo.persistence.models.products.request.CorreccionRequest;
import com.cumpleanos.mongo.service.interfaces.IProductoObservacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mongo")
@RequiredArgsConstructor
@Tag(name = "Producto Observacio", description = "Documentacion API Producto Correos")
public class ProductoObservacionController {

    private final IProductoObservacionService service;

    @Operation(summary = "Guardar", description = "Guardar Observacion de Producto")
    @PostMapping("/observacion/guardar")
    public ResponseEntity<ProductoObservacion> saveProductoObservacion(@RequestBody ProductoObservacion productoObservacion) {
        return ResponseEntity.ok(service.save(productoObservacion));
    }

    @Operation(summary = "Listar", description = "Listar Observaciones de Productos")
    @Parameter(name = "bodegaId", description = "Id de la bodega donde se encuentra la observacion del producto")
    @GetMapping("/observacion/listar/{bodegaId}")
    public ResponseEntity<List<ProductoObservacion>> listarProductoObservacion(@PathVariable Long bodegaId) {
        return ResponseEntity.ok(service.findByBodega(bodegaId));
    }

    @Operation(summary = "Correccion", description = "Agregar correccion de productos")
    @PutMapping("/observacion/agregar-correccion")
    public ResponseEntity<ProductoObservacion> agregarCorreccion(@RequestBody CorreccionRequest request) {
        return ResponseEntity.ok(service.addCorrection(request));
    }

}