package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.dto.BodegaDTO;
import com.cumpleanos.models.service.interfaces.IUsrBodService;
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
public class UsrBodController {
    private final IUsrBodService service;

    @GetMapping("bodegas/usuario/{usrId}/{empresa}")
    public ResponseEntity<Set<BodegaDTO>> listBodegasByUsr(@PathVariable Long usrId, @PathVariable Long empresa) {
        Set<BodegaDTO> bodegas = service.listBodByUser(usrId, empresa);
        return ResponseEntity.ok(bodegas);
    }
}
