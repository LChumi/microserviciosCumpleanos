package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.dto.CtipocomDTO;
import com.cumpleanos.models.service.interfaces.ICtipocomService;
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
public class CtipocomController {

    private final ICtipocomService service;

    @GetMapping("listar/ctipocom/{empresa}")
    public ResponseEntity<Set<CtipocomDTO>> listar(@PathVariable Long empresa) {
        Set<CtipocomDTO> lista = service.listCtipocomByEmpresa(empresa);
        return ResponseEntity.ok(lista);
    }

}
