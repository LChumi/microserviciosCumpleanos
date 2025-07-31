package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.TipoDoc;
import com.cumpleanos.models.service.interfaces.ITipoDocService;
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
public class TipoDocController {
    private final ITipoDocService service;

    @GetMapping("/tipodoc/listar")
    public ResponseEntity<Set<TipoDoc>> listarTipoDoc() {
        Set<TipoDoc> docs= service.listAllOrder();
        return ResponseEntity.ok(docs);
    }
}
