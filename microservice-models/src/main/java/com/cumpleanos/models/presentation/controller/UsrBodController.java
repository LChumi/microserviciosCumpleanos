package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.UsrBodDTO;
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
    public ResponseEntity<Set<UsrBodDTO>> listBodegasByUsr(@PathVariable Long usrId, @PathVariable Long empresa) {
        Set<UsrBodDTO> bodegas = service.listBodByUser(usrId, empresa);
        return ResponseEntity.ok(bodegas);
    }
}
