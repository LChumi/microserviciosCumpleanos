package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Autcliente;
import com.cumpleanos.models.service.interfaces.IAutClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AutClienteController {

    private final IAutClienteService service;

    @GetMapping("/get-autcliente/{nroAut}/{empresa}")
    public ResponseEntity<Autcliente> getAutCliente(@PathVariable String nroAut, @PathVariable Long empresa) {
        Autcliente aut= service.findByNroAutorizacion(nroAut, empresa);
        if (aut==null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(aut);
    }

    @PostMapping("save-autcliente")
    public ResponseEntity<Autcliente> saveAutCliente(@RequestBody Autcliente aut) {
        Autcliente autSave = service.save(aut);
        return ResponseEntity.status(HttpStatus.CREATED).body(autSave);
    }
}
