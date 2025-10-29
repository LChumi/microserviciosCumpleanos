package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.dtos.BodegaDTO;
import com.cumpleanos.models.service.interfaces.IBodegaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Bodega", description = "Documentacion API Bodega")
public class BodegaController {

    private final IBodegaService bodegaService;

    @Operation(summary = "Obtener Bodega Web", description = "Obtiene la bodega Web por empresa", tags = {"Bodega"})
    @Parameter(name = "empresa", description = "Codigo de empresa")
    @GetMapping("/bodega/web/{empresa}")
    public ResponseEntity<BodegaDTO> getBodegaWeb(@PathVariable Long empresa) {
        BodegaDTO bodega = bodegaService.getBodegaWeb(empresa);
        return ResponseEntity.ok(bodega);
    }

}
