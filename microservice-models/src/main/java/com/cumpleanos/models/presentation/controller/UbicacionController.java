package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Ubicacion;
import com.cumpleanos.models.service.interfaces.IUbicacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Ubicacion", description = "Documentacion API Ubicacion")
public class UbicacionController {

    private final IUbicacionService service;

    @Operation(summary = "Obtener ubicacaion ", description = "Obtiene la ubicacion por Nombre y empresa", tags = {"Ubicacion"})
    @Parameters({
            @Parameter(name = "empresa", description = "Codigo de la empresa", in = ParameterIn.PATH),
            @Parameter(name = "nombre", description = "Nombre de la ubicacion ", in = ParameterIn.PATH)
    })
    @GetMapping("/ubicacion/{emp}/{nombre}")
    public ResponseEntity<List<Ubicacion>> getUbicacionByNombre(@PathVariable Long emp, @PathVariable String nombre) {
        List<Ubicacion> founds = service.findByEmpresaAndNombre(emp, nombre);
        if (founds == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(founds);
    }
}
