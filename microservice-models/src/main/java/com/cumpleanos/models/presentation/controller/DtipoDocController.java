package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.DTipoDocDTO;
import com.cumpleanos.models.service.interfaces.IDtipoDocService;
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
public class DtipoDocController {

    private final IDtipoDocService service;

    @GetMapping("/dtipodoc/{empresa}/{tpdCodigo}")
    public ResponseEntity<Set<DTipoDocDTO>> getDtipoDoc(@PathVariable Long empresa, @PathVariable Long tpdCodigo){
        Set<DTipoDocDTO> docs = service.getDtipodocByEmpresaAndTpdCodigo(empresa, tpdCodigo);
        return ResponseEntity.ok(docs);
    }
}
