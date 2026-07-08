package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.service.interfaces.IRolMenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor
@Tag(name = "rol-menu", description = "Documentacion de Roles del Menus Assist")
public class RolMenuController {

    private final IRolMenuService service;

}