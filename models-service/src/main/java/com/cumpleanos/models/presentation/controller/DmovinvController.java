package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.models.service.interfaces.IDmovinvSerice;
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
@RequiredArgsConstructor
@RequestMapping("models")
@Tag(name = "Dmovinv", description = "Documentacion API Dmovinv")
public class DmovinvController {

    private final IDmovinvSerice service;

    @Operation(summary = "AgregarCanDespacho", description = "Agrego cantApr de un despacho")
    @Parameters({
            @Parameter(name = "cco", description = "Codigo del comprobante"),
            @Parameter(name = "producto", description = "Codigo del producto"),
            @Parameter(name = "cantidad", description = "Cantidad aprobada")
    })
    @GetMapping("/dmovinv/added-cant/despacho/{cco}/{producto}/{cantidad}")
    public ResponseEntity<ServiceResponse> addedCanAprDespacho(@PathVariable BigInteger cco, @PathVariable Long producto, @PathVariable Integer cantidad) {
        ServiceResponse response = service.actualizarCantidadDespachada(cco, producto, cantidad);
        return ResponseEntity.ok(response);
    }


}
