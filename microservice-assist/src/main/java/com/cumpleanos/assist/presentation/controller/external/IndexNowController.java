package com.cumpleanos.assist.presentation.controller.external;

import com.cumpleanos.assist.service.implementation.IndexNowService;
import com.cumpleanos.common.records.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "IndexNow", description = "Documentacion se servicios de indexacion IndexNow para apps del sistema ")
public class IndexNowController {

    private final IndexNowService service;

    @Operation(summary = "Envio de indexacion de paginas web")
    @Parameter(name = "app", description = "Nombre de la aplicacion web")
    @GetMapping("/index/app/{app}")
    public ResponseEntity<ServiceResponse> index(@PathVariable String app) {
        return ResponseEntity.ok(service.indexNow(app));
    }
}
