package com.cumpleanos.models.controller;

import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.models.service.ISistemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            Sistema sistema = sistemaService.findByRuc(ruc);
            log.info("RUC: " + sistema);
            return sistema != null ? ResponseEntity.ok(sistema) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
