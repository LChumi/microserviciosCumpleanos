package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.dto.ServiceResponse;
import com.cumpleanos.assist.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("assist")
@CrossOrigin("*")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    @GetMapping("forgot-password/{usrId}")
    public ResponseEntity<ServiceResponse> forgotPassword(@PathVariable String usrId) {
        ServiceResponse sv=usuarioService.recoveryPassword(usrId);
        return ResponseEntity.ok(sv);
    }
}
