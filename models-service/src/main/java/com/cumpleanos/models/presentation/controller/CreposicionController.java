package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.models.service.interfaces.ICreposicionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Creposicion", description = "Documentacion API Creposicion")
public class CreposicionController {

    private final ICreposicionService service;

    @Operation(summary = "Guardar Creposicion", description = "Crea un registro Creposicion en el sistema", tags = {"Creposicion"}, responses = {
            @ApiResponse(responseCode = "200", description = "Creposcion creada")
    })
    @PostMapping("/creposicion/save")
    public ResponseEntity<Creposicion> save(@RequestBody Creposicion creposicion) {
        Creposicion create= service.save(creposicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @Operation(summary = "Verifica existencia", description = "Verifica si Creposicion existe por la referencia y empresa", tags = {"Creposicion"})
    @Parameters({
            @Parameter(name = "referencia", description = "Referencia del documento"),
            @Parameter(name = "empresa", description = "Codigo de la empresa")
    })
    @GetMapping("/creposicion/find/{referencia}/{empresa}")
    public ResponseEntity<Boolean> find(@PathVariable("referencia") String referencia, @PathVariable("empresa") Long empresa) {
        return ResponseEntity.ok(service.existCreposicionByEmpresaAndReferencia(referencia, empresa));
    }

    @Operation(summary = "Actualiza creposicion", description = "Finalizacion de un pedido generado por la web o reposicion")
    @Parameters({
            @Parameter(name = "empresa", description = "Codigo empresa"),
            @Parameter(name = "codigo", description = "Codigo de Creposicion"),
            @Parameter(name = "usrliq", description = "Usr liquida"),
            @Parameter(name = "estado", description = "Estado de creposicion")
    })
    @GetMapping("/creposicion/update/{empresa}/{codigo}/{usrliq}/{estado}")
    public ResponseEntity<ServiceResponse> finalizarPedido (@PathVariable Long empresa, @PathVariable Long codigo, @PathVariable Long usrliq, @PathVariable Integer estado) {
        ServiceResponse result = service.finalizarPedido(empresa,codigo,usrliq, estado);
        return ResponseEntity.ok(result);
    }
}
