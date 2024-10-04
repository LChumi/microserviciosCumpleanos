package com.cumpleanos.models.controller;

import com.cumpleanos.models.service.ICparametService;
import core.cumpleanos.models.entities.Cparamet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class CparametController {

    private final ICparametService icparametService;

    @GetMapping("/get-paramet/{empresa}/{codigo}")
    public ResponseEntity<Cparamet> getParamet(@PathVariable Long empresa, @PathVariable Long codigo) {
        try {
            Cparamet cparamet = icparametService.findByValor(codigo, empresa);
            if (cparamet == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
            return ResponseEntity.ok(cparamet);
        } catch (Exception e) {
            log.error("Error al buscar Cparamet por ID message:{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
