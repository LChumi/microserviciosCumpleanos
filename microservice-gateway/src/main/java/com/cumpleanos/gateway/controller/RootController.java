package com.cumpleanos.gateway.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;

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

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public Mono<ResponseEntity<Resource>> getRobotsFile() {
        Resource resource = new ClassPathResource("static/robots.txt");
        return Mono.just(ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(Duration.ofDays(30)).cachePublic())
                .body(resource)
        );
    }

}