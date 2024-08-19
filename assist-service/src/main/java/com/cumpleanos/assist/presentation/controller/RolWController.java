package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.service.interfaces.IRolWService;
import com.cumpleanos.core.models.entities.RolW;
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
@Tag(name = "rolw", description = "Documentacion de Roles Web Assist")
public class RolWController {

    private final IRolWService service;

    @Operation(summary = "Listar todos los roles")
    @GetMapping("/rolw/all")
    public ResponseEntity<List<RolW>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Por ID", description = "Obtiene el rol por su ID")
    @Parameter(name = "id", description = "ID del menu", required = true)
    @GetMapping("/rolw/{id}")
    public ResponseEntity<RolW> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear", description = "Crear un nuevo menu")
    @PostMapping("/rolw")
    public ResponseEntity<RolW> create(@RequestBody RolW rol) {
        return ResponseEntity.ok(service.save(rol));
    }

    @Operation(summary = "Actualizar", description = "Actualizar un menu existente")
    @PutMapping("/rolw")
    public ResponseEntity<RolW> update(@RequestBody RolW rol) {
        return ResponseEntity.ok(service.save(rol));
    }

}