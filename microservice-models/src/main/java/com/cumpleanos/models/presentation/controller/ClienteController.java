package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.ClienteRecord;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;
import com.cumpleanos.models.persistence.dto.ClienteDTO;
import com.cumpleanos.models.service.interfaces.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClienteController {

    private final IClienteService service;

    @GetMapping("/cliente/ruc/{ruc}/{tipo}/{empresa}")
    public ResponseEntity<ClienteRecord> getCliente(@PathVariable String ruc, @PathVariable Short tipo, @PathVariable Long empresa) {
        Cliente cliente = service.findByCedulaRucAndEmpresa(ruc, tipo, empresa);
        if (cliente == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(new ClienteRecord(cliente.getId().getEmpresa(), cliente.getId().getCodigo(), cliente.getTipo(), cliente.getNombre(), cliente.getRucCedula(), cliente.getDireccion()));
    }

    @PostMapping("/cliente/new")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente clienteNew = service.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNew);
    }

    @GetMapping("/cliente/id/{cliId}/{empresa}")
    public ResponseEntity<List<String>> getClientes(@PathVariable String cliId, @PathVariable Long empresa) {
        List<String> ids = new ArrayList<>();
        List<Cliente> clientes = service.findByCliId(cliId, empresa);
        for (Cliente cliente : clientes) {
            ids.add(cliente.getCliId());
        }
        return ResponseEntity.ok(ids);
    }

    @GetMapping("/cliente/{empresa}/{tipo}")
    public ResponseEntity<Set<ClienteDTO>> getClientesXTipo(@PathVariable("empresa") Long empresa, @PathVariable("tipo") Short tipo) {
        Set<ClienteDTO> clientes = service.findByTipoAndEmpresa(tipo, empresa);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/cliente/ced/{empresa}/{clicodigo}")
    public ResponseEntity<String> getClienteCedById(@PathVariable("empresa") Long empresa, @PathVariable("clicodigo") Long codigo) {
        ClienteId id = new ClienteId();
        id.setCodigo(codigo);
        id.setEmpresa(empresa);
        Cliente cliente = service.findById(id);
        if (cliente == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cliente.getRucCedula());
    }
}