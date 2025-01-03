package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.entity.UsuarioFavoritos;
import com.cumpleanos.assist.service.interfaces.IUsuarioFavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioFavoritoController {

    private final IUsuarioFavoritoService service;

    @GetMapping("/favoritos/{usuario}")
    public ResponseEntity<Set<UsuarioFavoritos>> listByUser(@PathVariable("usuario") Long usuario) {
        Set<UsuarioFavoritos> favoritos = service.getFavoritosByUser(usuario);
        return ResponseEntity.ok(favoritos);
    }
}
