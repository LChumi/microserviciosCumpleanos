package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.models.service.interfaces.IDfacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DfacturaController {
    private final IDfacturaService service;

    @PostMapping("/dfac/new")
    public ResponseEntity<Boolean> create(@RequestBody Dfactura dfactura) {
        Dfactura nuevoDetalle = service.save(dfactura);
        return ResponseEntity.ok(nuevoDetalle != null);
    }
}
