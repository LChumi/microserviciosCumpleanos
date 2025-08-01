package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.models.service.interfaces.ISistemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class SistemaController {

    private final ISistemaService sistemaService;

    @GetMapping("/sistema/empresa/{ruc}")
    public ResponseEntity<Sistema> getEmpresa(@PathVariable String ruc) {
        Sistema sistema = sistemaService.findByRuc(ruc);
        log.info("RUC: {}", sistema);
        if (sistema == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(sistema);
    }

    @GetMapping("/sistema/id-empresa/{id}")
    public ResponseEntity<Sistema> getEmpresaById(@PathVariable Long id) {
        Sistema sistema = sistemaService.findById(id);
        return ResponseEntity.ok(sistema);
    }

    @GetMapping("/sistema/list/empresa-grupo/{empresa}/{excludeId}")
    public ResponseEntity<List<Sistema>> getListEmpresaGrupo(@PathVariable Long empresa, @PathVariable Long excludeId) {
        List<Sistema> empresas = sistemaService.findByIdEmpresaGrupoAndNotId(empresa,excludeId);
        return ResponseEntity.ok(empresas);
    }
}
