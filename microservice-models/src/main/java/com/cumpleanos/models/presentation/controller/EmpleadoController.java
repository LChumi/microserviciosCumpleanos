package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Empleado;
import com.cumpleanos.models.service.interfaces.IEmpleadoService;
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
@Slf4j
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
