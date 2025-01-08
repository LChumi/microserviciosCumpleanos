package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Cparamet;
import com.cumpleanos.models.service.interfaces.ICparametService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CparametController {

    private final ICparametService icparametService;

    @GetMapping("/get-paramet/{empresa}/{codigo}")
    public ResponseEntity<Cparamet> getParamet(@PathVariable Long empresa, @PathVariable Long codigo) {
        Cparamet cparamet = icparametService.findByValor(codigo, empresa);
        if (cparamet == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(cparamet);
    }
}
