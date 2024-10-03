package com.cumpleanos.models.controller;

import com.cumpleanos.models.service.IAutClienteService;
import core.cumpleanos.models.entities.Autcliente;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AutClienteController {

    private final IAutClienteService service;

    @GetMapping("/get-autcliente/{nroAut}/{empresa}")
    public ResponseEntity<Autcliente> getAutCliente(@PathVariable String nroAut, @PathVariable Long empresa) {
        try {
            Autcliente aut= service.findByNroAutorizacion(nroAut, empresa);
            if (aut==null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.ok(aut);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
