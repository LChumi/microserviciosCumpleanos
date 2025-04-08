package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.AlmacenDTO;
import com.cumpleanos.core.models.ids.AlmacenId;
import com.cumpleanos.models.service.interfaces.IAlmacenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AlmacenController {

    private final IAlmacenService service;

    @GetMapping("/almacenes/{empresa}")
    public ResponseEntity<Set<AlmacenDTO>> listarAlmacenes(@PathVariable("empresa") Long empresa) {
        Set<AlmacenDTO> almacenes = service.listByEmpresa(empresa);
        return ResponseEntity.ok(almacenes);
    }

    @GetMapping("/almacen-get/{empresa}/{codigo}")
    public ResponseEntity<AlmacenDTO> getById(@PathVariable Long empresa, @PathVariable Long codigo){
        AlmacenId id = new AlmacenId();
        id.setEmpresa(empresa);
        id.setCodigo(codigo);
        Optional<AlmacenDTO> almacen = service.getById(id);
        return almacen.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
