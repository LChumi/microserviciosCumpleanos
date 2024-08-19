package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.service.interfaces.IProgramaWService;
import com.cumpleanos.core.models.entities.ProgramaW;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor
@Tag(name = "programaw", description = "Documentacion de UIRoutes o web interfaces de Assist")
public class ProgramaWController {

    private final IProgramaWService service;

    @Operation(summary = "Listar todos los UI routes")
    @GetMapping("/programaw/all")
    public ResponseEntity<List<ProgramaW>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Por ID", description = "Obtiene el UI route por su ID")
    @Parameter(name = "id", description = "ID del menu", required = true)
    @GetMapping("/programaw/{id}")
    public ResponseEntity<ProgramaW> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear", description = "Crear un nuevo UI route")
    @PostMapping("/programaw")
    public ResponseEntity<ProgramaW> create(@RequestBody ProgramaW menu) {
        return ResponseEntity.ok(service.save(menu));
    }

    @Operation(summary = "Actualizar", description = "Actualizar un UI route existente")
    @PutMapping("/programaw")
    public ResponseEntity<ProgramaW> update(@RequestBody ProgramaW menu) {
        return ResponseEntity.ok(service.save(menu));
    }

}