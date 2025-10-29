package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.ids.DreposicionId;
import com.cumpleanos.models.service.interfaces.IDreposicionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Dreposicion", description = "Documentacion API Dreposicion")
public class DreposicionController {

    private final IDreposicionService service;

    @Operation(summary = "Crear Dreposicion", description = "Crea la reposicion en el sistema", tags = {"Dreposicion"} , responses = {
            @ApiResponse(responseCode = "200", description = "Dreposicion creada")
    })
    @PostMapping("/dreposicion/save")
    public ResponseEntity<Dreposicion> save(@RequestBody Dreposicion dreposicion) {
        Dreposicion dreposicionSave = service.save(dreposicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(dreposicionSave);
    }

    @Operation(summary = "Obtener Dreposicion", description = "Obtiene Dreposicion por codigo y empresa ")
    @GetMapping("/dreposicion/get/{codigo}/{empresa}")
    public ResponseEntity<Dreposicion> getDreposicion(@PathVariable Long codigo, @PathVariable Long empresa) {
        DreposicionId id = new DreposicionId();
        id.setCodigo(codigo);
        id.setEmpresa(empresa);
        Dreposicion dreposicion = service.findById(id);
        return ResponseEntity.ok(dreposicion);
    }
}
