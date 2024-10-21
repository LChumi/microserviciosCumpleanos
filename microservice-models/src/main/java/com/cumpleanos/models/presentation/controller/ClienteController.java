package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.entities.Ubicacion;
import com.cumpleanos.core.models.ids.UbicacionId;
import com.cumpleanos.models.persistence.repository.UbicacionRepository;
import com.cumpleanos.models.service.implementation.ClienteServiceImpl;
import com.cumpleanos.models.service.interfaces.IClienteService;
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
    private IClienteService clienteServiceImpl;

    @GetMapping("/cliente/ruc/{ruc}/{tipo}/{empresa}")
    public ResponseEntity<Cliente> getCliente(@PathVariable String ruc, @PathVariable Short tipo, @PathVariable Long empresa) {
        Cliente cliente = clienteServiceImpl.findByCedulaRucAndEmpresa(ruc,tipo ,empresa);
        if (cliente == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("cliente/new")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente clienteNew = clienteServiceImpl.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNew);
    }

    @GetMapping("clientesid/{cliId}/{empresa}")
    public ResponseEntity<List<String>> getClientes(@PathVariable String cliId, @PathVariable Long empresa) {
        List<String> ids = new ArrayList<>();
        List<Cliente> clientes = clienteServiceImpl.findByCliId(cliId, empresa);
        for (Cliente cliente : clientes) {
            ids.add(cliente.getCliId());
        }
        return ResponseEntity.ok(ids);
    }
}
