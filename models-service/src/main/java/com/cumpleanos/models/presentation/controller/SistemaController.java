package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.models.service.interfaces.ISistemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Tag(name = "Sistema", description = "Documentacion API de empresas")
public class SistemaController {

    private final ISistemaService sistemaService;

    @Operation(summary = "Obtener Empresa Ruc", description = "Obtiene la informacion de la empresa por el Ruc", tags = {"Sistema"}, responses = {
            @ApiResponse(responseCode = "200", description = "Empresa")
    })
    @Parameter(name = "ruc", description = "Ruc de la empresa")
    @GetMapping("/sistema/empresa/{ruc}")
    public ResponseEntity<Sistema> getEmpresa(@PathVariable String ruc) {
        Sistema sistema = sistemaService.findByRuc(ruc);
        log.info("RUC: {}", sistema);
        if (sistema == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(sistema);
    }

    @Operation(summary = "Obtener Empresa Codigo", description = "Obtiene la informacion de la empresa por su codigo", tags = {"Sistema"}, responses = {
            @ApiResponse(responseCode = "200", description = "Empresa")
    })
    @Parameter(name = "id", description = "Codigo de la empresa")
    @GetMapping("/sistema/id-empresa/{id}")
    public ResponseEntity<Sistema> getEmpresaById(@PathVariable Long id) {
        Sistema sistema = sistemaService.findById(id);
        return ResponseEntity.ok(sistema);
    }

    @Operation(summary = "Obtiene el grupo de empresas", description = "Obtiene la informacion del grupo de empresas que pertenece excluyendo la consultada", tags = {"Sistema"}, responses = {
            @ApiResponse(responseCode = "200", description = "Lista empresas")
    })
    @Parameters({
            @Parameter(name = "empresa", description = "codigo de empresa", in = ParameterIn.PATH, required = true),
            @Parameter(name = "excludeId", description = "codigo de la empresa a excluir", in = ParameterIn.PATH, required = true)
    })
    @GetMapping("/sistema/list/empresa-grupo/{empresa}/{excludeId}")
    public ResponseEntity<List<Sistema>> getListEmpresaGrupo(@PathVariable Long empresa, @PathVariable Long excludeId) {
        List<Sistema> empresas = sistemaService.findByIdEmpresaGrupoAndNotId(empresa,excludeId);
        return ResponseEntity.ok(empresas);
    }
}
