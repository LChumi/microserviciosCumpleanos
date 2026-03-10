package com.cumpleanos.mongo.presentation.controller;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.common.records.SessionDTO;
import com.cumpleanos.mongo.service.interfaces.ISessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mongo")
@RequiredArgsConstructor(onConstructor_ =  @Autowired)
@Tag(name = "Sesiones", description = "Documentacion API Sessiones de Usuarios")
public class SessionController {

    private final ISessionService sessionService;

    @Operation(summary = "Guardar sesion", description = "Guardar la sesion de un Usuario")
    @PostMapping("session/saved")
    public ResponseEntity<ServiceResponse> saveSession(@RequestBody SessionDTO sessionDTO) {
        ServiceResponse response = sessionService.saveSession(sessionDTO);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Ultima sesion", description = "Obtiene la ultima sesion guardada")
    @Parameter(name = "userId", description = "Codigo de usuario")
    @GetMapping("seesion/last-login/{userId}")
    public ResponseEntity<ServiceResponse> getLastLogin(@PathVariable String userId) {
        ServiceResponse response = sessionService.getLastLogin(userId);
        return ResponseEntity.ok(response);
    }

}