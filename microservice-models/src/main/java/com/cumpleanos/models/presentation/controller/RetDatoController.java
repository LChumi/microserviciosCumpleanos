package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.RetDato;
import com.cumpleanos.models.service.interfaces.IRetDatoService;
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
public class RetDatoController {

    private final IRetDatoService retDatoService;

    @GetMapping("/retdato/{empresa}/{tablacoa}/{id}")
    public ResponseEntity<RetDato> getRetDato(@PathVariable("empresa") Long empresa, @PathVariable("tablacoa") Long tablacoa, @PathVariable("id") String id) {
        RetDato retDato = retDatoService.getRetDatoByEmpresaTablacoaId(empresa, tablacoa, id);
        if (retDato == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(retDato);
    }
}
