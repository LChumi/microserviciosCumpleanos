package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.entity.UsuarioFavoritos;
import com.cumpleanos.assist.persistence.inmutables.FavoriteRequest;
import com.cumpleanos.assist.service.interfaces.IUsuarioFavoritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add/favoritos")
    public ResponseEntity<UsuarioFavoritos> addFavorito(@RequestBody @Valid FavoriteRequest request) {
        UsuarioFavoritos favorito = service.saveFavorito(request);
        return ResponseEntity.ok(favorito);
    }
}
