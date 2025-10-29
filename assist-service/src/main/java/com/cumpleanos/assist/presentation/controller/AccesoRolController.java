package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.transformers.MenuTransformer;
import com.cumpleanos.assist.service.interfaces.IAccesoRolService;
import com.cumpleanos.core.models.entities.Sistema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Accesos", description = "Documentacion Accesos a menus segun rol")
public class AccesoRolController {

    private final IAccesoRolService service;

    @Operation(summary = "Lista de menus de usuarios por empresa")
    @Parameter(name = "usuario", description = "Codigo usuario", required = true)
    @Parameter(name = "empresa", description = "Codigo empresa", required = true)
    @GetMapping("/acceso-rol/menus/{usuario}/{empresa}")
    public ResponseEntity<Set<MenuTransformer>> getMenus(@PathVariable Long usuario, @PathVariable Long empresa) {
        Set<MenuTransformer> menus = service.obtenerMenusYSubmenus(usuario, empresa);
        return ResponseEntity.ok(menus);
    }

    @Operation(summary = "Lista de empresas asignadas al usuario")
    @Parameter(name = "usuario", description = "Codigo de usuario", required = true)
    @GetMapping("/acceso-rol/empresas/{usuario}")
    public ResponseEntity<Set<Sistema>> getEmpresas(@PathVariable Long usuario) {
        Set<Sistema> sistemas = service.getEmpresas(usuario);
        return ResponseEntity.ok(sistemas);
    }
}