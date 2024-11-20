package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.dto.MenuDTO;
import com.cumpleanos.assist.service.interfaces.IAccesoRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("assist")
@CrossOrigin("*")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AccesoRolController {

    private final IAccesoRolService service;

    @GetMapping("/menus/{usuario}/{empresa}")
    public ResponseEntity<Set<MenuDTO>> getMenus(@PathVariable Long usuario, @PathVariable Long empresa) {
        Set<MenuDTO> menus =service.obtenerMenusYSubmenus(usuario, empresa);
        return ResponseEntity.ok(menus);
    }
}