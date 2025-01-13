package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.dto.AlmacenDTO;
import com.cumpleanos.models.service.interfaces.IAlmacenService;
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
public class AlmacenController {

    private final IAlmacenService service;

    @GetMapping("/almacenes/{empresa}")
    public ResponseEntity<Set<AlmacenDTO>> listarAlmacenes(@PathVariable("empresa") Long empresa) {
        Set<AlmacenDTO> almacenes = service.listByEmpresa(empresa);
        return ResponseEntity.ok(almacenes);
    }
}
