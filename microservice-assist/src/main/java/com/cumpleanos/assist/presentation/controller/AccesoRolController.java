package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.dto.MenuDTO;
import com.cumpleanos.assist.service.interfaces.IAccesoRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AccesoRolController {

    private final IAccesoRolService service;

    @GetMapping("/menus/{usuario}/{empresa}")
    public Set<MenuDTO> getMenus(@PathVariable Long usuario, @PathVariable Long empresa) {
        return service.obtenerMenusYSubmenus(usuario, empresa);
    }
}