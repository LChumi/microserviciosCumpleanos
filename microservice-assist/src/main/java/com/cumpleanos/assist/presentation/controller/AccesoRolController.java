package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.transformers.MenuTransformer;
import com.cumpleanos.assist.service.interfaces.IAccesoRolService;
import com.cumpleanos.core.models.entities.Sistema;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AccesoRolController {

    private final IAccesoRolService service;

    @GetMapping("/menus/{usuario}/{empresa}")
    public ResponseEntity<Set<MenuTransformer>> getMenus(@PathVariable Long usuario, @PathVariable Long empresa) {
        Set<MenuTransformer> menus =service.obtenerMenusYSubmenus(usuario, empresa);
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/empresas/{usuario}")
    public ResponseEntity<Set<Sistema>> getEmpresas(@PathVariable Long usuario) {
        Set<Sistema> sistemas = service.getEmpresas(usuario);
        return ResponseEntity.ok(sistemas);
    }
}