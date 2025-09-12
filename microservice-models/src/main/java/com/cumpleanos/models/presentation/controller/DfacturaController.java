package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.DfacturaDTO;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.models.service.interfaces.IDfacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

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

    @Operation(summary = "Validar existencia Dfactura ", description = "Obtiene el detalle de la factura si existe el producto y el comprobante cco")
    @Parameters({
            @Parameter(name = "cco", description = "Codigo del comprobante"),
            @Parameter(name = "producto", description = "Codigo del producto")
    })
    @GetMapping("/dfactura/get/{cco}/{producto}/")
    public ResponseEntity<ServiceResponse> getDetalle(@PathVariable BigInteger cco, @PathVariable Long producto) {
        ServiceResponse response = service.validateQuantities(cco, producto);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtener Dfactura", description = "Obtiene el DTO de Dfactura ")
    @Parameters({
            @Parameter(name = "cco", description = "Codigo del comprobante"),
            @Parameter(name = "producto", description = "Codigo del producto")
    })
    @GetMapping("/dfactura/getBy/{cco}/{producto}")
    public ResponseEntity<DfacturaDTO> getDfactura(@PathVariable BigInteger cco, @PathVariable Long producto) {
        DfacturaDTO fac = service.getDfactura(cco, producto);
        return ResponseEntity.ok(fac);
    }

    @Operation(summary = "AgregarCan", description = "Agrego cantApr al detalle de la factura ")
    @Parameters({
            @Parameter(name = "cco", description = "Codigo del comprobante"),
            @Parameter(name = "producto", description = "Codigo del producto"),
            @Parameter(name = "cantidad", description = "Cantidad aprovada")
    })
    @GetMapping("/dfactura/added-cant/{cco}/{producto}/{cantidad}")
    public ResponseEntity<ServiceResponse> addedCanApr(@PathVariable BigInteger cco, @PathVariable Long producto,@PathVariable Integer cantidad) {
        ServiceResponse reponse = service.addCantApr(cco, producto, cantidad);
        return ResponseEntity.ok(reponse);
    }
}
