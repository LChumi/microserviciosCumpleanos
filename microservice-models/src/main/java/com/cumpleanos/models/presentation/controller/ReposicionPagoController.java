package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.ReposicionPago;
import com.cumpleanos.models.service.interfaces.IReposicionPagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Reposicion Pago", description = "Documentacion API de Reposicion Pago")
public class ReposicionPagoController {

    private final IReposicionPagoService service;

    @Operation(summary = "Crear", description = "Crea Resposicion Pago en el sistema", tags = {"Reposicion Pago"})
    @PostMapping("/reposicion/crear-pago")
    public ResponseEntity<ReposicionPago> createPago(@RequestBody ReposicionPago reposicionPago) {
        ReposicionPago nuevoPago = service.save(reposicionPago);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPago);
    }

    @Operation(summary = "Verifica si existe", description = "Verifica si existe informacion o no se encuentra registrado por id creposicion y empresa", tags = {"Reposicion Pago"})
    @Parameters({
            @Parameter(name = "id", description = "Codigo Creposicion", in = ParameterIn.PATH, required = true),
            @Parameter(name = "emp", description = "Codigo empresa", in = ParameterIn.PATH, required = true)
    })
    @GetMapping("/reposicion/{id}/empresa/{emp}")
    public ResponseEntity<Boolean> getReposicionPago(@PathVariable Long id, @PathVariable Long emp){
        ReposicionPago found = service.getByCreposicionAndEmpresa(id, emp);
        if (found == null) {
            return ResponseEntity.ok(Boolean.FALSE);
        }
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
