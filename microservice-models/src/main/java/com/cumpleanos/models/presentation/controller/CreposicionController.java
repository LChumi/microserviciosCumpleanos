package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.models.service.interfaces.ICreposicionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CreposicionController {

    private final ICreposicionService service;

    @PostMapping("/creposicion/save")
    public ResponseEntity<Creposicion> save(@RequestBody Creposicion creposicion) {
        Creposicion create= service.save(creposicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @GetMapping("/creposicion/find/{codigo}/{empresa}")
    public ResponseEntity<Boolean> find(@PathVariable("codigo") Long codigo, @PathVariable("empresa") Long empresa) {
        return ResponseEntity.ok(service.existCreposicion(empresa, codigo));
    }
}
