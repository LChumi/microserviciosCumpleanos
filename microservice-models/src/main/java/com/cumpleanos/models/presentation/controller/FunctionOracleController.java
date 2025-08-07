package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.models.service.implementation.FunctionOracleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Funciones Oracle", description = "Operaciones relacionadas con funciones en Oracle")
public class FunctionOracleController {

    private final FunctionOracleServiceImpl functionService;

    @Operation(summary = "Funcion verificar juridico", tags = {"Funciones Oracle"}, description = "Funcion que verifica el tipo del cliente por su Ruc", responses = {
            @ApiResponse(responseCode = "200", description = "Tipo de cliente numero")
    })
    @Parameter(name = "ruc", description = "Ruc del cliente")
    @GetMapping("/function-oralce/verificarJuridico/{ruc}")
    public ResponseEntity<Long> verificarJuridico(@PathVariable String ruc) {
        Long response = functionService.verificarRucJuridico(ruc);
        if (response == null) {
            return ResponseEntity.ok(0L);
        }
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Funcion obtener Parametro", tags = {"Funciones Oracle"}, description = "Funcion que retorna el parametro de funciones", responses = {
            @ApiResponse(responseCode = "200", description = "Parametro numero")
    })
    @GetMapping("/function-oracle/parametro/{empresa}/{sigla}/{secuencia}/{tipo}")
    public ResponseEntity<Long> verificarParametro(@PathVariable Long empresa, @PathVariable String sigla, @PathVariable String secuencia, @PathVariable int tipo) {
        Long parametro = functionService.agenteParametro(empresa, sigla, secuencia, null, tipo);
        if (parametro == null) {
            return ResponseEntity.ok(0L);
        }
        return ResponseEntity.ok(parametro);
    }
}
