package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.service.interfaces.IMenuWService;
import com.cumpleanos.core.models.entities.MenuW;
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
@Tag(name = "menuw", description = "Documentacion de Menus Assist")
public class MenuWController {

    private final IMenuWService service;

    @Operation(summary = "Listar todos los menus")
    @GetMapping("/menuw/all")
    public ResponseEntity<List<MenuW>> getAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Por ID", description = "Obtiene el menu por su ID")
    @Parameter(name = "id", description = "ID del menu", required = true)
    @GetMapping("/menuw/{id}")
    public ResponseEntity<MenuW> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear", description = "Crear un nuevo menu")
    @PostMapping("/menuw")
    public ResponseEntity<MenuW> create(@RequestBody MenuW menu){
        return ResponseEntity.ok(service.save(menu));
    }

    @Operation(summary = "Actualizar", description = "Actualizar un menu existente")
    @PutMapping("/menuw")
    public ResponseEntity<MenuW> update(@RequestBody MenuW menu){
        return ResponseEntity.ok(service.save(menu));
    }

}
