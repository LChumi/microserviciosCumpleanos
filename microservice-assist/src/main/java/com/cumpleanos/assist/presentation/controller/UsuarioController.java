package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.auth.AuthenticationRequest;
import com.cumpleanos.assist.persistence.dto.ServiceResponse;
import com.cumpleanos.assist.persistence.dto.UserResponse;
import com.cumpleanos.assist.service.implementation.UsuarioServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    @GetMapping("/forgot-password/{usrId}")
    public ResponseEntity<ServiceResponse> forgotPassword(@PathVariable String usrId) {
        ServiceResponse sv=usuarioService.recoveryPassword(usrId);
        return ResponseEntity.ok(sv);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        UserResponse user = usuarioService.login(request);
        if(user==null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.accepted().body(user);
    }
}
