package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.TipoDoc;
import com.cumpleanos.models.service.interfaces.ITipoDocService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "TipoDoc", description = "Documentacion API TipoDoc")
public class TipoDocController {
    private final ITipoDocService service;

    @Operation(summary = "Listar tipoDoc", description = "Listar tiposDocs")
    @GetMapping("/tipodoc/listar")
    public ResponseEntity<Set<TipoDoc>> listarTipoDoc() {
        Set<TipoDoc> docs= service.listAllOrder();
        return ResponseEntity.ok(docs);
    }
}
