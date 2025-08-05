package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.entity.UsuarioFavoritos;
import com.cumpleanos.assist.persistence.inmutables.FavoriteRequest;
import com.cumpleanos.assist.service.interfaces.IUsuarioFavoritoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Favoritos", description = "Documentacion API de paginas menus favoritos de usuarios accesos directos")
public class UsuarioFavoritoController {

    private final IUsuarioFavoritoService service;

    @Operation(summary = "Lista favoritos de usuario y empresa")
    @Parameters({
            @Parameter(name = "usuario", description = "ID del usuario", in = ParameterIn.PATH),
            @Parameter(name = "empresa", description = "Código de la empresa", in = ParameterIn.PATH)
    })
    @GetMapping("/favoritos/{usuario}/{empresa}")
    public ResponseEntity<Set<UsuarioFavoritos>> listByUser(@PathVariable("usuario") Long usuario, @PathVariable("empresa") Long empresa) {
        Set<UsuarioFavoritos> favoritos = service.getFavoritosByUserAndEmpresa(usuario, empresa);
        return ResponseEntity.ok(favoritos);
    }

    @Operation(summary = "Agregar favoritos por usuario", description = "Registra una nueva página como favorita para el usuario especificado")
    @PostMapping("/favoritos/add")
    public ResponseEntity<UsuarioFavoritos> addFavorito(
            @RequestBody @Valid FavoriteRequest request) {
        UsuarioFavoritos favorito = service.saveFavorito(request);
        return ResponseEntity.ok(favorito);
    }

    @Operation(summary = "Obtiene favoritos por usuario", description = "Obtiene los favoritos del usuario")
    @PostMapping("/favoritos/get")
    public ResponseEntity<Boolean> getFavorite(@RequestBody @Valid FavoriteRequest request) {
        boolean isFavorite = service.getFavoritoByUsuarioEmpresaPath(request) != null;
        return ResponseEntity.ok(isFavorite);
    }

    @Operation(summary = "Elimina favoritos por usuario", description = "Elimina los favoritos del usuario")
    @DeleteMapping("/favoritos/delete")
    public ResponseEntity<Void> deleteFavorito(@RequestBody @Valid FavoriteRequest request) {
        service.deleteFavoritoByUsuarioEmpresaPath(request);
        return ResponseEntity.noContent().build();
    }

}
