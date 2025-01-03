package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.dto.ClienteDTO;
import com.cumpleanos.assist.service.interfaces.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ClienteController {

    private final IClienteService clienteService;

    @GetMapping("/clientes/{empresa}/{tipo}")
    public ResponseEntity<Set<ClienteDTO>> getClientesXTipo(@PathVariable("empresa") Long empresa, @PathVariable("tipo") Short tipo) {
        Set<ClienteDTO> clientes = clienteService.findByTipoAndEmpresa(tipo, empresa);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/cliente/{empresa}/{tipo}/{categoria}")
    public ResponseEntity<Set<ClienteDTO>> getClientesXtipoXcategoria(@PathVariable("empresa") Long empresa, @PathVariable("tipo") Short tipo, @PathVariable("categoria") Long categoria) {
        Set<ClienteDTO> clientes = clienteService.findByEmpresaTipoAndCategoria(empresa, tipo, categoria);
        return ResponseEntity.ok(clientes);
    }
}
