package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.dto.ClienteRecord;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.models.service.interfaces.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteController {

    private final IClienteService clienteServiceImpl;

    @GetMapping("/cliente/ruc/{ruc}/{tipo}/{empresa}")
    public ResponseEntity<ClienteRecord> getCliente(@PathVariable String ruc, @PathVariable Short tipo, @PathVariable Long empresa) {
        Cliente cliente = clienteServiceImpl.findByCedulaRucAndEmpresa(ruc,tipo ,empresa);
        if (cliente == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(new ClienteRecord(cliente.getId().getEmpresa(), cliente.getId().getCodigo(), cliente.getTipo(), cliente.getNombre(), cliente.getRucCedula(), cliente.getDireccion()));
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
