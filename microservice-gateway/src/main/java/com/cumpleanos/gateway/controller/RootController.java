package com.cumpleanos.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class RootController {

    @GetMapping("/")
    public Mono<Void> info(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.SEE_OTHER);
        exchange.getResponse().getHeaders().setLocation(
                exchange.getRequest().getURI().resolve("/actuator/info")
        );
        return exchange.getResponse().setComplete();
    }

}