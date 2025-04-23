package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Dreposicion;
import com.cumpleanos.core.models.ids.DreposicionId;
import com.cumpleanos.models.service.interfaces.IDreposicionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DreposicionController {

    private final IDreposicionService service;

    @PostMapping("/save/dreposicion")
    public ResponseEntity<Dreposicion> save(Dreposicion dreposicion) {
        Dreposicion dreposicionSave = service.save(dreposicion);
        return ResponseEntity.status(HttpStatus.CREATED).body(dreposicionSave);
    }

    @GetMapping("/get-creposicion/{codigo}/{empresa}")
    public ResponseEntity<Dreposicion> getDreposicion(@PathVariable Long codigo, @PathVariable Long empresa) {
        DreposicionId id = new DreposicionId();
        id.setCodigo(codigo);
        id.setEmpresa(empresa);
        Dreposicion dreposicion = service.findById(id);
        return ResponseEntity.ok(dreposicion);
    }
}
