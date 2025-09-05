package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.models.service.interfaces.ICcomprobaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Ccomproba", description = "Documentacion API Ccomproba")
public class CcomprobaController {

    private final ICcomprobaService service;

    @Operation(summary = "AgregarReferencia", description = "Agrega cco de Referencia en el comprobante")
    @Parameters({
            @Parameter(name = "cco", description = "Codigo del comprobante"),
            @Parameter(name = "ref", description = "Referencia del codigo"),
            @Parameter(name = "empresa", description = "Codigo de empresa al cambio")
    })
    @GetMapping("/cco/update/referencia/{cco}/{ref}/{empresa}")
    public ResponseEntity<Boolean> updateReferencia(@PathVariable BigInteger cco, @PathVariable BigInteger ref, @PathVariable Long empresa) {
        Boolean response = service.addReference(cco, ref, empresa);
        return ResponseEntity.ok(response);
    }
}
