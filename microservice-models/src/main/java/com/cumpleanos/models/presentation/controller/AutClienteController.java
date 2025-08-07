package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Autcliente;
import com.cumpleanos.models.service.interfaces.IAutClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@Tag(name = "AutCliente", description = "Documentacion API AutCliente")
public class AutClienteController {

    private final IAutClienteService service;

    @Operation(summary = "Obtener AuthCliente", description = "Obtener AuthCliente por numero de autorizacion y empresa", tags = {"AutCliente"})
    @Parameters({
            @Parameter(name = "nroAut", description = "Numero de Autorizacion", in = ParameterIn.PATH, required = true),
            @Parameter(name = "empresa", description = "Empresa", in = ParameterIn.PATH, required = true)
    })
    @GetMapping("/autcliente/get/{nroAut}/{empresa}")
    public ResponseEntity<Autcliente> getAutCliente(@PathVariable String nroAut, @PathVariable Long empresa) {
        Autcliente aut= service.findByNroAutorizacion(nroAut, empresa);
        if (aut==null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(aut);
    }

    @Operation(summary = "Crear AutCliente", description = "Crear AutCliente en el sistema", tags = {"AutCliente"}, responses = {
            @ApiResponse(responseCode = "200", description = "AutCliente creado" )
    })
    @PostMapping("/autcliente/save")
    public ResponseEntity<Autcliente> saveAutCliente(@RequestBody Autcliente aut) {
        Autcliente autSave = service.save(aut);
        return ResponseEntity.status(HttpStatus.CREATED).body(autSave);
    }
}
