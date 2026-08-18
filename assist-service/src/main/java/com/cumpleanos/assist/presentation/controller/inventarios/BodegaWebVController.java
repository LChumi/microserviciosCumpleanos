package com.cumpleanos.assist.presentation.controller.inventarios;

import com.cumpleanos.assist.service.implementation.inventario.BodegaWebVServiceImp;
import com.cumpleanos.core.models.views.BodegaWebV;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("assist")
@Tag(name = "BodegaWebV", description = "Documentacion de BodegaWebV")
public class BodegaWebVController {

    private final BodegaWebVServiceImp service;

    @Operation(summary = "Listar Bodegas", description = "Lista todas las bodegas de un usuario y empresa")
    @GetMapping("/bodegawebv/{usuario}/{empresa}/bodegas")
    public ResponseEntity<List<BodegaWebV>> listarBodegas(@PathVariable Long usuario, @PathVariable Long empresa) {
        return ResponseEntity.ok(service.findByUsuarioAndEmpresa(usuario, empresa));
    }
}
