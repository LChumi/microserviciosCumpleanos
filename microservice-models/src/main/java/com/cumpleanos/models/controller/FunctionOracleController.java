package com.cumpleanos.models.controller;

import com.cumpleanos.models.service.functions.FunctionOracleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@Slf4j
@RequiredArgsConstructor
public class FunctionOracleController {

    private final FunctionOracleServiceImpl functionService;

    @GetMapping("/verificarJuridico/{ruc}")
    public ResponseEntity<Long> verificarJuridico(@PathVariable String ruc) {
        try {
            Long response = functionService.verificarRucJuridico(ruc);
            if (response == null) {
                return ResponseEntity.ok(0L);
            }
            return ResponseEntity.ok(response);
        }catch (Exception e) {
            log.error("Error al verificar si el ruc es juridico message:{}",e.getMessage());
            return ResponseEntity.ok(0L);
        }
    }

    @GetMapping("/parametro/{empresa}/{sigla}/{secuencia}/{tipo}")
    public ResponseEntity<Long> verificarParametro(@PathVariable int empresa, @PathVariable String sigla, @PathVariable String secuencia, @PathVariable int tipo) {
        try {
            Long parametro = functionService.agenteParametro(empresa, sigla, secuencia, null, tipo);
            if (parametro == null) {
                return ResponseEntity.ok(0L);
            }
            return ResponseEntity.ok(parametro);
        }catch (Exception e) {
            log.error("Error al verificar el parametro mesage:{}",e.getMessage());
            return ResponseEntity.ok(0L);
        }
    }
}
