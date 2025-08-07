package com.cumpleanos.pos.presentation.controller;

import com.cumpleanos.pos.service.interfaces.IReciboPOSSyncService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("pos")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "DataPos", description = "Documentacion API Datafast")
public class ReciboPOSSyncController {

    private final IReciboPOSSyncService service;

    @Operation(summary = "Procesar Pago COM", description = "Conecta y procesa el Pago via puerto COM", tags = {"DataPos"})
    @Parameters({
            @Parameter(name = "UsrLiq", description = "Codigo de Usuario Liquida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/procesarPago/{usr}/{empresa}")
    public ResponseEntity<String> procesarPago(@PathVariable Long usr, @PathVariable Long empresa) {
        String response = service.procesarPago(usr, empresa);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Lista Puertos COM", description = "Lista los puertos COM del cliente", tags = {"DataPos"})
    @Parameters({
            @Parameter(name = "UsrLiq", description = "Codigo de Usuario Liquida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/listarPuertos/{usr}/{empresa}")
    public ResponseEntity<Map<String, String>> listarPuertos(@PathVariable Long usr, @PathVariable Long empresa) {
        Map<String, String> response = service.listarPuertos(usr, empresa);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Anular Pago COM", description = "Anula los pagos a Datapos", tags = {"DataPos"})
    @Parameters({
            @Parameter(name = "UsrLiq", description = "Codigo de Usuario Liquida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/anularPago/{usr}/{empresa}")
    public ResponseEntity<String> anularPago(@PathVariable Long usr, @PathVariable Long empresa) {
        String response = service.anularPago(usr, empresa);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Procesar Pago LAN", description = "Conecta y procesa el Pago via LAN", tags = {"DataPos"})
    @Parameters({
            @Parameter(name = "UsrLiq", description = "Codigo de Usuario Liquida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/procesarPagoLan/{usr}/{empresa}")
    public ResponseEntity<String> procesarPagoLan(@PathVariable Long usr, @PathVariable Long empresa) {
        String response = service.procesarPagoLan(usr, empresa);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Anular Pago LAN", description = "Anula los pagos a Datapos vian LAN", tags = {"DataPos"})
    @Parameters({
            @Parameter(name = "UsrLiq", description = "Codigo de Usuario Liquida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/anularPagoLan/{usr}/{empresa}")
    public ResponseEntity<String> anularPagoLan(@PathVariable Long usr, @PathVariable Long empresa) {
        String response = service.anularPagoLan(usr, empresa);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
