package com.cumpleanos.mongo.presentation.controller;

import com.cumpleanos.common.records.CompanyParametersRecord;
import com.cumpleanos.mongo.persistence.models.company.CompanyParameters;
import com.cumpleanos.mongo.service.interfaces.ICompanyParametersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/mongo")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "company", description = "Documentacion API Parametros empresa")
public class CompanyParametersController {

    private final ICompanyParametersService service;

    @Operation(summary = "Lista de todos los parametos", description = "Lista los parametros de todas las empresas registradas", tags = {"company"})
    @GetMapping("/company")
    public ResponseEntity<List<CompanyParameters>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Crea nuevos Parametros", description = "Crea una nueva empresa con sus parametros")
    @PostMapping("/company/save")
    public ResponseEntity<CompanyParameters> save(@RequestBody CompanyParameters companyParameters) {
        return ResponseEntity.ok(service.save(companyParameters));
    }

    @Operation(summary = "Obtiene el Parametro", description = "Obtiene el Parametro consultado por su id", tags = {"company"})
    @Parameter(name = "id", description = "Id del Parametro")
    @GetMapping("/company/{companyId}")
    public ResponseEntity<CompanyParameters> getByCompanyId(@PathVariable Long companyId) {
        return ResponseEntity.ok(service.getByCompanyId(companyId));
    }

    @PutMapping("/company/{companyId}/logo/{tipo}")
    public ResponseEntity<CompanyParametersRecord> uploadLogo(
            @PathVariable Long companyId,
            @PathVariable Long tipo,
            @RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok(service.updateLogoBase64(companyId, tipo, file));
    }
}
