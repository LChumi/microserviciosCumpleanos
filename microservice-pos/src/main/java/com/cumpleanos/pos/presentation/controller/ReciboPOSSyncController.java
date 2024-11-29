package com.cumpleanos.pos.presentation.controller;

import com.cumpleanos.pos.service.interfaces.IReciboPOSSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pos/")
@Slf4j
@RequiredArgsConstructor
public class ReciboPOSSyncController {

    private final IReciboPOSSyncService service;

    @GetMapping("procesarPago/{usr}/{empresa}")
    public ResponseEntity<String> procesarPago(@PathVariable Long usr, @PathVariable Long empresa) {
        String response = service.procesarPago(usr, empresa);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("listarPuertos/{usr}/{empresa}")
    public ResponseEntity<Map<String,String>> listarPuertos(@PathVariable Long usr, @PathVariable Long empresa) {
        Map<String,String> response = service.listarPuertos(usr, empresa);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("anularPago/{usr}/{empresa}")
    public ResponseEntity<String> anularPago(@PathVariable Long usr, @PathVariable Long empresa) {
        String response = service.anularPago(usr, empresa);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
