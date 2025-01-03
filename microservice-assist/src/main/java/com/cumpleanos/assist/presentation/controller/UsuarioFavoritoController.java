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

    @GetMapping("/favoritos/{usuario}/{empresa}")
    public ResponseEntity<Set<UsuarioFavoritos>> listByUser(@PathVariable("usuario") Long usuario, @PathVariable("empresa") Long empresa) {
        Set<UsuarioFavoritos> favoritos = service.getFavoritosByUserAndEmpresa(usuario, empresa);
        return ResponseEntity.ok(favoritos);
    }

    @PostMapping("/favoritos/add")
    public ResponseEntity<UsuarioFavoritos> addFavorito(@RequestBody @Valid FavoriteRequest request) {
        UsuarioFavoritos favorito = service.saveFavorito(request);
        return ResponseEntity.ok(favorito);
    }

    @PostMapping("/favoritos/get")
    public ResponseEntity<Boolean> getFavorite(@RequestBody @Valid FavoriteRequest request) {
        boolean isFavorite = service.getFavoritoByUsuarioEmpresaPath(request) != null;
        return ResponseEntity.ok(isFavorite);
    }



}
