package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.auth.AuthenticationRequest;
import com.cumpleanos.assist.persistence.inmutables.UserResponse;
import com.cumpleanos.assist.service.exception.BadCredentialsException;
import com.cumpleanos.assist.service.implementation.UsuarioServiceImpl;
import com.cumpleanos.common.records.ServiceResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthController {

    private final UsuarioServiceImpl usuarioService;

    @GetMapping("/auth/forgot-password/{usrId}")
    public ResponseEntity<ServiceResponse> forgotPassword(@PathVariable String usrId) {
        ServiceResponse sv = usuarioService.recoveryPassword(usrId);
        return ResponseEntity.ok(sv);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        UserResponse user = usuarioService.login(request);
        if (user == null) {
            throw new BadCredentialsException("Usuario no autenticado");
        }
        return ResponseEntity.accepted().body(user);
    }
}
