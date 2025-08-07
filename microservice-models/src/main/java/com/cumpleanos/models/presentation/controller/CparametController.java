package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Cparamet;
import com.cumpleanos.models.service.interfaces.ICparametService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cparamet", description = "Documentacion API Cparamet")
public class CparametController {

    private final ICparametService icparametService;

    @Operation(summary = "Obtener Parametro", description = "Obtengo un parametro por empresa y valor")
    @Parameters({
            @Parameter(name = "empresa", description = "Codigo de empresa", in = ParameterIn.PATH, required = true),
            @Parameter(name = "codigo", description = "Codigo del valor", in = ParameterIn.PATH, required = true),
    })
    @GetMapping("/cparamet/get/{empresa}/{codigo}")
    public ResponseEntity<Cparamet> getParamet(@PathVariable Long empresa, @PathVariable Long codigo) {
        Cparamet cparamet = icparametService.findByValor(codigo, empresa);
        if (cparamet == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(cparamet);
    }
}
