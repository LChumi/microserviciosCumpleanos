package com.cumlpeanos.pos.controller;

import com.cumlpeanos.pos.service.IReciboPOSSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pos/")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class ReciboPOSSyncController {

    private final IReciboPOSSyncService service;

    @GetMapping("procesarPago/{usr}/{empresa}")
    public ResponseEntity<String> procesarPago(@PathVariable Long usr, @PathVariable Long empresa) {
        try {
            String response = service.procesarPago(usr, empresa);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            log.error("ERROR: en el servicio procesar Pago, message:{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("listarPuertos/{usr}/{empresa}")
    public ResponseEntity<Map<String,String>> listarPuertos(@PathVariable Long usr, @PathVariable Long empresa) {
        try {
            Map<String,String> response = service.listarPuertos(usr, empresa);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            log.error("ERROR: en el servicio listarPuertos, message:{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("anularPago/{usr}/{empresa}")
    public ResponseEntity<String> anularPago(@PathVariable Long usr, @PathVariable Long empresa) {
        try {
            String response = service.anularPago(usr, empresa);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            log.error("ERROR: en el servicio anularPago, message:{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
