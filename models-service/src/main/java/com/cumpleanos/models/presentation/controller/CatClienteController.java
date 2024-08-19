package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.CatCliente;
import com.cumpleanos.models.service.interfaces.ICatClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "CatCliente", description = "Docuemtacion API CatCliente")
public class CatClienteController {
    private final ICatClienteService catClienteService;

    @Operation
    @Parameter(name = "empresa", description = "Codigo empresa", in = ParameterIn.PATH, required = true)
    @GetMapping("/catcliente/{empresa}")
    public ResponseEntity<Set<CatCliente>> listByEmpresa(@PathVariable("empresa") Long empresa) {
        Set<CatCliente> categorias = catClienteService.listByEmpresa(empresa);
        return ResponseEntity.ok(categorias);
    }
}
