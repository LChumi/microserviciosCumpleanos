package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Usuario;
import com.cumpleanos.models.service.interfaces.IUsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Tag(name = "Usuario", description = "Documentacion API de usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("userId") String userId){
        Usuario usuario = usuarioService.findByUsername(userId);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/usuario/{userId}/{clave}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable("userId") String userId, @PathVariable("clave") String clave){
        Usuario usuario = usuarioService.findByUsrIdAnClave(userId, clave);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/usuario/codigo/{codigo}")
    public ResponseEntity<Usuario> getUsuarioByCodigo(@PathVariable("codigo") Long codigo){
        Usuario usuario = usuarioService.findById(codigo);
        return ResponseEntity.ok(usuario);
    }
}