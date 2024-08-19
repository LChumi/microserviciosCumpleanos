package com.cumpleanos.assist.presentation.controller.importaciones;

import com.cumpleanos.assist.persistence.views.ComImpV1;
import com.cumpleanos.assist.service.interfaces.importaciones.IComImpV1Service;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompImpV1Controller {

    private final IComImpV1Service service;

    @GetMapping("compimp/{empresa}")
    @Operation(summary = "Carga de archivos para Solicitud de importaciones")
    @Parameter(name = "empresa", description = "Codigo empresa", required = true)
    public ResponseEntity<List<ComImpV1>> getImportacionesIni(@PathVariable Long empresa) {
        List<ComImpV1> importaciones = service.getImportaciones(empresa);
        return ResponseEntity.ok(importaciones);
    }
}
