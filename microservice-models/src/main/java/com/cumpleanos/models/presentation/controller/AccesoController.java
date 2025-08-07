package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.AccesoDTO;
import com.cumpleanos.models.service.interfaces.IAccesoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name ="Accesos", description = "Documentacion API de accesos ")
public class AccesoController {

    private final IAccesoService service;

    @Operation(summary = "Lista de accesos", description = "Lista de accesos de usuario por empresa ")
    @Parameters({
            @Parameter(name = "usuario", description = "Codigo de usuario"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/acceso/{usuario}/{empresa}")
    public ResponseEntity<AccesoDTO> getAccesoByUsuarioAndEmpresa(@PathVariable Long usuario, @PathVariable Long empresa) {
        AccesoDTO acceso = service.getAccesoByUsuarioAndEmpresa(usuario, empresa);
        return ResponseEntity.ok(acceso);
    }
}
