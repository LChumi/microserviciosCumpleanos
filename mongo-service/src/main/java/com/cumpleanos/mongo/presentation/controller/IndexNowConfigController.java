package com.cumpleanos.mongo.presentation.controller;

import com.cumpleanos.mongo.persistence.models.app.IndexNowConfig;
import com.cumpleanos.mongo.service.interfaces.IIndexNowConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mongo")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "indexNow", description = "Documentacion API Indexacion APP")
public class IndexNowConfigController {

    private final IIndexNowConfigService service;

    @Operation(summary = "Crear app", description = "Crear app para indexacion")
    @PostMapping("/save/index-now")
    public ResponseEntity<IndexNowConfig>  saveIndexNowConfig(@RequestBody IndexNowConfig indexNowConfig) {
        return ResponseEntity.ok(service.save(indexNowConfig));
    }

    @Operation(summary = "Agregar rutas", description = "Agregar rutas para indexacion")
    @Parameters({
            @Parameter(name = "name", description = "Nombre de la app"),
            @Parameter(name = "route", description = "Nueva ruta a agregar")
    })
    @PutMapping("/add/route/{name}/{route}")
    public ResponseEntity<IndexNowConfig>  addRoute(@PathVariable String name, @PathVariable String route) {
        return ResponseEntity.ok(service.addRoute(name, route));
    }

    @Operation(summary = "Quitar rutas", description = "Agregar rutas para indexacion")
    @Parameters({
            @Parameter(name = "name", description = "Nombre de la app"),
            @Parameter(name = "route", description = "Nueva ruta a quitar")
    })
    @DeleteMapping("/remove/route/{name}/{route}")
    public ResponseEntity<IndexNowConfig>  removeRoute(@PathVariable String name, @PathVariable String route) {
        return ResponseEntity.ok(service.removeRoute(name, route));
    }
}
