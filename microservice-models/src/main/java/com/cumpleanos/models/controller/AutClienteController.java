package com.cumpleanos.models.controller;

import com.cumpleanos.core.models.entities.Autcliente;
import com.cumpleanos.models.service.IAutClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("save-autcliente")
    public ResponseEntity<Autcliente> saveAutCliente(@RequestBody Autcliente aut) {
        try {
            Autcliente autSave = service.save(aut);
            return ResponseEntity.status(HttpStatus.CREATED).body(autSave);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
