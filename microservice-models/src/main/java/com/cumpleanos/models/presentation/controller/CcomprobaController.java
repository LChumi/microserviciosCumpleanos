package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.models.service.interfaces.ICcomprobaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CcomprobaController {
    private final ICcomprobaService service;

    @PutMapping("update/cco")
    public ResponseEntity<Boolean> updateCco(Long empresa , BigInteger codigo, Long bodega) {
        Boolean result = service.updateBodegaCco(empresa, codigo, bodega);
        return ResponseEntity.ok(result);
    }
}
