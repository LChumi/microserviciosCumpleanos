package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.DreposicionDTO;
import com.cumpleanos.common.records.RevisionProductoRequest;
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

import java.util.List;

@Slf4j
@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Dreposicion", description = "Documentacion API Dreposicion")
public class DreposicionController {

    private final IDreposicionService service;

    @Operation(summary = "Crear Dreposicion", description = "Crea la reposicion en el sistema", tags = {"Dreposicion"}, responses = {
            @ApiResponse(responseCode = "200", description = "Dreposicion creada")
    })
    @PostMapping("/dreposicion/save")
    public ResponseEntity<DreposicionDTO> save(@RequestBody Dreposicion dreposicion) {
        DreposicionDTO dreposicionSave = service.saveDetail(dreposicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(dreposicionSave);
    }

    @Operation(summary = "Eliminar Dreposicion", description = "Elimina dreposicion en intento fallido")
    @DeleteMapping("/dreposicion/delete")
    public ResponseEntity<Void> deleteDreposicion(@RequestBody DreposicionId id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Obtener Lista", description = "Obtiene Lista Dreposicion por codigo cabecera creposicion ")
    @GetMapping("/dreposicion/get/{creposicion}")
    public ResponseEntity<List<DreposicionDTO>> getDreposicion(@PathVariable Long creposicion) {
        List<DreposicionDTO> dreposicion = service.getProductsByCreposicion(creposicion);
        return ResponseEntity.ok(dreposicion);
    }

    @Operation(summary = "Obtener detalle", description = "Obtener el producto de dreposicon por su barra")
    @PostMapping("/dreposicion/getByBarra")
    public ResponseEntity<DreposicionDTO> getByBarra(@RequestBody RevisionProductoRequest request) {
        DreposicionDTO d = service.quantityAddedPerCreposicionAndProduct(request);
        return ResponseEntity.ok(d);
    }
}