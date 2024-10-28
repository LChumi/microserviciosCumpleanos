package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.models.service.interfaces.ISistemaService;
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
@Slf4j
public class SistemaController {

    @Autowired
    private ISistemaService sistemaService;

    @GetMapping("/empresa/{ruc}")
    public ResponseEntity<Sistema> getEmpresa(@PathVariable String ruc) {
        Sistema sistema = sistemaService.findByRuc(ruc);
        log.info("RUC: " + sistema);
        if (sistema == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(sistema);
    }
}
