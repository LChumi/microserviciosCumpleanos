package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.models.service.implementation.FunctionOracleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FunctionOracleController {

    private final FunctionOracleServiceImpl functionService;

    @GetMapping("/function-oralce/verificarJuridico/{ruc}")
    public ResponseEntity<Long> verificarJuridico(@PathVariable String ruc) {
        Long response = functionService.verificarRucJuridico(ruc);
        if (response == null) {
            return ResponseEntity.ok(0L);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/function-oracle/parametro/{empresa}/{sigla}/{secuencia}/{tipo}")
    public ResponseEntity<Long> verificarParametro(@PathVariable Long empresa, @PathVariable String sigla, @PathVariable String secuencia, @PathVariable int tipo) {
        Long parametro = functionService.agenteParametro(empresa, sigla, secuencia, null, tipo);
        if (parametro == null) {
            return ResponseEntity.ok(0L);
        }
        return ResponseEntity.ok(parametro);
    }
}
