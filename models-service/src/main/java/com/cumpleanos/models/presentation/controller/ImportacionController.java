package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.ImportacionDTO;
import com.cumpleanos.models.service.interfaces.IImportacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor
@Tag(name = "Impotacion", description = "Detalles cabecera de importacion")
public class ImportacionController {

    private final IImportacionService service;

    @Operation(summary = "Obtener Importacion", description = "Obtener Cabecera de Importacion")
    @Parameters({
            @Parameter(name = "cco", description = "Codigo de importacion"),
            @Parameter(name = "empresa", description = "Codigo de empresa"),
    })
    @GetMapping("/importacion/get/{cco}/{empresa}")
    public ResponseEntity<ImportacionDTO> getImportacion(@PathVariable BigInteger cco, @PathVariable Long empresa) {
        ImportacionDTO dto = service.getById(cco, empresa);
        return ResponseEntity.ok(dto);
    }
}
