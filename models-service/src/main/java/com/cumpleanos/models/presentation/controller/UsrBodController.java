package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.common.records.UsrBodDTO;
import com.cumpleanos.models.service.interfaces.IUsrBodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "UsrBod", description = "Documentacion API UsrBod")
public class UsrBodController {
    private final IUsrBodService service;

    @Operation(summary = "Obtener lista de bodegas", description = "Obtiene la lista de Bodegas por usuario y empresa", tags = {"UsrBod"})
    @Parameters({
            @Parameter(name = "usrId", description = "Codigo de Usuario", in = ParameterIn.PATH),
            @Parameter(name = "empresa", description = "Codigo de la empresa", in = ParameterIn.PATH)
    })
    @GetMapping("/usrbod/bodegas/{usrId}/{empresa}")
    public ResponseEntity<Set<UsrBodDTO>> listBodegasByUsr(@PathVariable Long usrId, @PathVariable Long empresa) {
        Set<UsrBodDTO> bodegas = service.listBodByUser(usrId, empresa);
        return ResponseEntity.ok(bodegas);
    }
}
