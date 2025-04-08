package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.AccesoDTO;
import com.cumpleanos.models.service.interfaces.IAccesoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AccesoController {

    private final IAccesoService service;

    @GetMapping("/acceso/{usuario}/{empresa}")
    public ResponseEntity<AccesoDTO> getAccesoByUsuarioAndEmpresa(@PathVariable Long usuario, @PathVariable Long empresa) {
        AccesoDTO acceso = service.getAccesoByUsuarioAndEmpresa(usuario, empresa);
        return ResponseEntity.ok(acceso);
    }
}
