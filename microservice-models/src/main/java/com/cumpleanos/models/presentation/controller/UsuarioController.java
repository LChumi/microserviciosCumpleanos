package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Usuario;
import com.cumpleanos.models.service.interfaces.IUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("userId") String userId){
        Usuario usuario = usuarioService.findByUsername(userId);
        if(usuario == null){
            log.error("Usuario no encontrado {}", userId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuario);
    }
}