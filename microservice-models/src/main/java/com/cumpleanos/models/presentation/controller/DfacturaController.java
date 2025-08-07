package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.models.service.interfaces.IDfacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Dfactura", description = "Documentacion API Dfactura")
public class DfacturaController {
    private final IDfacturaService service;

    @Operation(summary = "Crea Dfactura", description = "Crea el detalle de un comprobante", tags = {"Dfactura"}, responses = {
            @ApiResponse(responseCode = "200", description = "Detalle de la factura")
    })
    @PostMapping("/dfactura/new")
    public ResponseEntity<Boolean> create(@RequestBody Dfactura dfactura) {
        Dfactura nuevoDetalle = service.save(dfactura);
        return ResponseEntity.ok(nuevoDetalle != null);
    }
}
