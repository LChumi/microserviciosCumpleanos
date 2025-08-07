package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.RetDato;
import com.cumpleanos.models.service.interfaces.IRetDatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
@Tag(name = "RetDato", description = "Documetacion API RetDato")
public class RetDatoController {

    private final IRetDatoService retDatoService;

    @Operation(summary = "Obtener RetDato por id", tags = {"RetDato"}, description = "Obtener RetDato por Id ,empresa y tablacoa ")
    @Parameters({
            @Parameter(name = "empresa", description = "Codigo de la empresa"),
            @Parameter(name = "tablacoa", description = "Codigo tablacoa"),
            @Parameter(name = "id", description = "Id de RetDato")
    })
    @GetMapping("/retdato/{empresa}/{tablacoa}/{id}")
    public ResponseEntity<RetDato> getRetDato(@PathVariable("empresa") Long empresa, @PathVariable("tablacoa") Long tablacoa, @PathVariable("id") String id) {
        RetDato retDato = retDatoService.getRetDatoByEmpresaTablacoaId(empresa, tablacoa, id);
        if (retDato == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(retDato);
    }
}
