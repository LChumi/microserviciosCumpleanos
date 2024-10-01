package com.cumpleanos.models.controller;

import com.cumpleanos.models.service.ClienteService;
import core.cumpleanos.models.entities.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente/ruc/{ruc}/{empresa}")
    public ResponseEntity<Cliente> getCliente(@PathVariable String ruc, @PathVariable Long empresa) {
        try {
            Cliente cliente = clienteService.findByCedulaRucAndEmpresa(ruc, empresa);
            if (cliente == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(cliente);
        }catch (Exception e) {
            log.error("Error al buscar el cliente por Ruc :{}", ruc, e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("cliente/new")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente clienteNew = clienteService.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteNew);
        } catch (Exception e) {
            log.error("Error al crear el cliente :{}", cliente, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
