package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.CtipocomDTO;
import com.cumpleanos.models.service.interfaces.ICtipocomService;
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
@Tag(name = "CtipoCom", description = "Documentacion API Ctipocom")
public class CtipocomController {

    private final ICtipocomService service;

    @Operation(summary = "listar ctipoCom", description = "Lista los CtipoCom", tags = {"CtipoCom"})
    @Parameter(name = "empresa", description = "Codigo de empresa", in = ParameterIn.PATH)
    @GetMapping("/ctipocom/listar/{empresa}")
    public ResponseEntity<Set<CtipocomDTO>> listar(@PathVariable Long empresa) {
        Set<CtipocomDTO> lista = service.listCtipocomByEmpresa(empresa);
        return ResponseEntity.ok(lista);
    }

}
