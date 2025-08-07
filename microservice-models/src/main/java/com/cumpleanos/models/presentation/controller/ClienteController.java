package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.ClienteRecord;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;
import com.cumpleanos.models.persistence.dto.ClienteDTO;
import com.cumpleanos.models.service.interfaces.IClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cliente", description = "Documentacion Api de clientes")
public class ClienteController {

    private final IClienteService service;

    @Operation(summary = "Obtener cliente", tags = {"Cliente"}, description = "Obtener Cliente por ruc tipo y empresa")
    @Parameters({
            @Parameter(name = "ruc", description = "Ruc o cedula de cliente"),
            @Parameter(name = "tipo", description = "Tipo de cliente"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/cliente/ruc/{ruc}/{tipo}/{empresa}")
    public ResponseEntity<ClienteRecord> getCliente(@PathVariable String ruc, @PathVariable Short tipo, @PathVariable Long empresa) {
        Cliente cliente = service.findByCedulaRucAndEmpresa(ruc, tipo, empresa);
        if (cliente == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(new ClienteRecord(cliente.getId().getEmpresa(), cliente.getId().getCodigo(), cliente.getTipo(), cliente.getNombre(), cliente.getRucCedula(), cliente.getDireccion()));
    }

    @Operation(summary = "Crear Clientes",tags = {"Cliente"}, description = "Registrar nuevos clientes")
    @PostMapping("/cliente/new")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente clienteNew = service.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNew);
    }

    @Operation(summary = "Obtener los Ids",tags = {"Cliente"}, description = "Obtiene la lista de ids existentes de clientes por empresa")
    @Parameters({
            @Parameter(name = "cliId", description = "Id del cliente"),
            @Parameter(name = "empresa", description = "Codigo de la empresa")
    })
    @GetMapping("/cliente/id/{cliId}/{empresa}")
    public ResponseEntity<List<String>> getClientes(@PathVariable String cliId, @PathVariable Long empresa) {
        List<String> ids = new ArrayList<>();
        List<Cliente> clientes = service.findByCliId(cliId, empresa);
        for (Cliente cliente : clientes) {
            ids.add(cliente.getCliId());
        }
        return ResponseEntity.ok(ids);
    }

    @Operation(summary = "Lista clientes",tags = {"Cliente"}, description = "Lista de clientes por empresa y tipo")
    @GetMapping("/cliente/{empresa}/{tipo}")
    public ResponseEntity<Set<ClienteDTO>> getClientesXTipo(@PathVariable("empresa") Long empresa, @PathVariable("tipo") Short tipo) {
        Set<ClienteDTO> clientes = service.findByTipoAndEmpresa(tipo, empresa);
        return ResponseEntity.ok(clientes);
    }

    @Operation(summary = "Obtener cedula",tags = {"Cliente"}, description = "Retorna la cedula del cliente")
    @Parameters({
            @Parameter(name = "empresa", description = "Codigo de la empresa"),
            @Parameter(name = "clicodigo", description = "Codigo de cliente")
    })
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