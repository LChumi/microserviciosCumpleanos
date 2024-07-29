package com.cumlpeanos.pos.controller;

import com.cumlpeanos.pos.models.entity.ReciboPOS;
import com.cumlpeanos.pos.service.IReciboPOSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/pos/recibo/")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class ReciboPOSController {

    private final IReciboPOSService reciboPOSService;

    @GetMapping("id/{id}/empresa/{empresa}")
    public ResponseEntity<ReciboPOS> porIdYEmpresa(@PathVariable Long id, @PathVariable Long empresa) {
        try {
            ReciboPOS reciboPOS= reciboPOSService.findByIdAndEmpresa(id, empresa);
            if (reciboPOS == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(reciboPOS);
        }catch (Exception e){
            log.error("ERROR en el servicio al buscar por ID y Empresa message:{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("cco/{cco}")
    public ResponseEntity<ReciboPOS> porCco(@PathVariable BigInteger cco) {
        try {
            ReciboPOS reciboPOS= reciboPOSService.findByCcoComproba(cco);
            if (reciboPOS == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(reciboPOS);
        }catch (Exception e){
            log.error("Error al buscar por CCO message:{}",e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
