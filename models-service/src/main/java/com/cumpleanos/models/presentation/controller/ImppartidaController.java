package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Imppartida;
import com.cumpleanos.models.service.interfaces.IImppartidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Imppartida", description = "Documentacion API de Partidas de Importacion")
public class ImppartidaController {

    private final IImppartidaService service;

    @Operation(summary = "Obtener Partida", description = "Partida de importaciones por empresa con su Id")
    @Parameters({
            @Parameter(name = "id", description = "Id de importacion"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/imppartida/get/by-id")
    public ResponseEntity<Imppartida> getByIdAndEmpresa(
            @RequestParam("id") String id,
            @RequestParam("empresa") Long empresaId)
    {
        Imppartida imppartida = service.getByImIdAndEmpresa(id, empresaId);
        return ResponseEntity.ok(imppartida);
    }


}
