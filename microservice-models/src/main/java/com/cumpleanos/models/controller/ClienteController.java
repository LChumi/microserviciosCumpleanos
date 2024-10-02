package com.cumpleanos.models.controller;

import com.cumpleanos.models.service.ClienteServiceImpl;
import core.cumpleanos.models.entities.Cliente;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("models")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteServiceImpl;

    @GetMapping("/cliente/ruc/{ruc}/{empresa}")
    public ResponseEntity<Cliente> getCliente(@PathVariable String ruc, @PathVariable Long empresa) {
        try {
            Cliente cliente = clienteServiceImpl.findByCedulaRucAndEmpresa(ruc, empresa);
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
            Cliente clienteNew = clienteServiceImpl.save(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteNew);
        } catch (Exception e) {
            log.error("Error al crear el cliente :{}", cliente, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("clientesid/{cliId}/{empresa}")
    public ResponseEntity<List<String>> getClientes(@PathVariable String cliId, @PathVariable Long empresa) {
        try {
            List<String> ids = new ArrayList<>();
            List<Cliente> clientes = clienteServiceImpl.findByCliId(cliId, empresa);
            for (Cliente cliente : clientes) {
                ids.add(cliente.getCliId());
            }
            return ResponseEntity.ok(ids);
        }catch (Exception e) {
            log.error("Error al buscar el clientes :{}", cliId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
