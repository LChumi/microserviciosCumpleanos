package com.cumpleanos.meta.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RootController {

    @GetMapping("/")
    public RedirectView info() {
        return new RedirectView("/actuator/info");
    }

    @GetMapping("/docs")
    public RedirectView swaggerUi() {
        return new RedirectView("/swagger-ui.html");
    }

}
