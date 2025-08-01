package com.cumpleanos.config.microservice_config.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RootController {

    @GetMapping("/")
    public RedirectView info() {
        return new RedirectView("/actuator/info");
    }

}
