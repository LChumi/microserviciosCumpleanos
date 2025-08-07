package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.models.service.interfaces.IEmpleadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Empleado", description = "Documentacion API Empleado")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    @Operation(summary = "Obtener empleado", description = "Obtener el empleado por el codigo de usuario")
    @Parameter(name = "usuario Id", description = "Codigo de usuario")
    @GetMapping("/empleado/id-usuario/{usuarioId}")
    public ResponseEntity<Empleado> getEmpleadoByUsuarioId(@PathVariable Long usuarioId) {
        Empleado encontrado = empleadoService.findByUsuario(usuarioId);
        if (encontrado == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(encontrado);
    }
}
