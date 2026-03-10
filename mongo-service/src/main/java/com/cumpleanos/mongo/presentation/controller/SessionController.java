package com.cumpleanos.mongo.presentation.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mongo")
@RequiredArgsConstructor(onConstructor_ =  @Autowired)
@Tag(name = "Sesiones", description = "Documentacion API Sessiones de Usuarios")
public class SessionController {


}