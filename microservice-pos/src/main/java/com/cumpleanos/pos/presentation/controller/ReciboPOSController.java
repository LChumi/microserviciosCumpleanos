package com.cumlpeanos.pos.presentation.controller;

import com.cumlpeanos.pos.persistence.entity.ReciboPOS;
import com.cumlpeanos.pos.persistence.ids.ReciboPOSId;
import com.cumlpeanos.pos.service.interfaces.IReciboPOSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/pos/")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
public class ReciboPOSController {

    private final IReciboPOSService reciboPOSService;

    @GetMapping("recibo/id/{id}/empresa/{empresa}")
    public ResponseEntity<ReciboPOS> porIdYEmpresa(@PathVariable Long id, @PathVariable Long empresa) {
        ReciboPOS reciboPOS= reciboPOSService.findByIdAndEmpresa(id, empresa);
        if (reciboPOS == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(reciboPOS);
    }

    @GetMapping("recibo/cco/{cco}")
    public ResponseEntity<ReciboPOS> porCco(@PathVariable BigInteger cco) {
        ReciboPOS reciboPOS= reciboPOSService.findByCcoComproba(cco);
        if (reciboPOS == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(reciboPOS);
    }

    @PostMapping("recibo/id")
    public ResponseEntity<ReciboPOS> porReciboId(@RequestBody ReciboPOSId id) {
        ReciboPOS reciboPOS = reciboPOSService.getReciboPOS(id);
        return ResponseEntity.ok(reciboPOS);
    }
}
