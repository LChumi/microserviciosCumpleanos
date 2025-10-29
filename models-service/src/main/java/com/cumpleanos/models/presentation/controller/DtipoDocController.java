package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.DTipoDocDTO;
import com.cumpleanos.models.service.interfaces.IDtipoDocService;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "DtipoDoc", description = "Documentacion API DtipoDoc")
public class DtipoDocController {

    private final IDtipoDocService service;

    @Operation(summary = "Obtener ")
    @GetMapping("/dtipodoc/{empresa}/{tpdCodigo}")
    public ResponseEntity<Set<DTipoDocDTO>> getDtipoDoc(@PathVariable Long empresa, @PathVariable Long tpdCodigo){
        Set<DTipoDocDTO> docs = service.getDtipodocByEmpresaAndTpdCodigo(empresa, tpdCodigo);
        return ResponseEntity.ok(docs);
    }
}
