package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.service.interfaces.IRolMenuService;
import com.cumpleanos.core.models.entities.RolMenu;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("assist")
@Tag(name = "rol-menu", description = "Documentacion de Roles del Menus Assist")
public class RolMenuController {

    private final IRolMenuService service;

    @Operation(summary = "Listar todos los roles")
    @GetMapping("/rol-menu/all")
    public ResponseEntity<List<RolMenu>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Por ID", description = "Obtiene el rol por su ID")
    @Parameter(name = "id", description = "ID del menu", required = true)
    @GetMapping("/rol-menu/{id}")
    public ResponseEntity<RolMenu> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear", description = "Crear un nuevo menu")
    @PostMapping("/rol-menu")
    public ResponseEntity<RolMenu> create(@RequestBody RolMenu rol){
        return ResponseEntity.ok(service.save(rol));
    }

    @Operation(summary = "Actualizar", description = "Actualizar un menu existente")
    @PutMapping("/rol-menu")
    public ResponseEntity<RolMenu> update(@RequestBody RolMenu rol){
        return ResponseEntity.ok(service.save(rol));
    }

}