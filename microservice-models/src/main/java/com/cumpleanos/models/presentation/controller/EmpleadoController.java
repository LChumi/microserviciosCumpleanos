package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.models.service.interfaces.IEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    @GetMapping("/empleado/id-usuario/{usuarioId}")
    public ResponseEntity<Empleado> getEmpleadoByUsuarioId(@PathVariable Long usuarioId) {
        Empleado encontrado = empleadoService.findByUsuario(usuarioId);
        if (encontrado == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(encontrado);
    }
}
