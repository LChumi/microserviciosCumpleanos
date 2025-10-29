package com.cumpleanos.reccomprobantes.presentation.controller;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.reccomprobantes.service.implementation.CoordinatorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("recp")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Recepcion Comprobantes", description = "Docuemntacion API recepcion de Documentos SRI")
public class FilesProcessingController {

    private final CoordinatorService service;

    @Operation(summary = "Carga archivo", description = "Carga el archivo xsl, csv, txt, json con informacion de Documentos emitidos del SRI y procesa la informacion en el sistema", tags = {"Recepcion Comprobantes"})
    @Parameters({
            @Parameter(name = "email", description = "Correo del usuario que realiza el proceso")
    })
    @PostMapping("/file")
    public ResponseEntity<ServiceResponse> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("email") String email) throws IOException {
        Object result = service.processFile(file, email);

        return service.getServiceResponseResponseEntity(result);

    }
    @Operation(summary = "Carga data", description = "Se sube la informacion en texto", tags = {"Recepcion Comprobantes"})
    @Parameters({
            @Parameter(name = "email", description = "Correo del usuario que realiza el proceso")
    })
    @PostMapping("/string")
    public ResponseEntity<ServiceResponse> processString(@RequestBody String string,
                                           @RequestParam("email") String email) throws IOException {
        Object result = service.processString(string, email);

        return service.getServiceResponseResponseEntity(result);
    }

}