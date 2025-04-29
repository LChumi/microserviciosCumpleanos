package com.cumpleanos.mongo.presentation.controller;

import com.cumpleanos.mongo.persistence.models.company.CompanyParameters;
import com.cumpleanos.mongo.service.interfaces.ICompanyParametersService;
import com.cumpleanos.mongo.utils.LogoType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/mongo")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyParametersController {

    private final ICompanyParametersService service;

    @GetMapping("/company")
    public ResponseEntity<List<CompanyParameters>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/company/save")
    public ResponseEntity<CompanyParameters> save(@RequestBody CompanyParameters companyParameters) {
        return ResponseEntity.ok(service.save(companyParameters));
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<CompanyParameters> getByCompanyId(@PathVariable Long companyId) {
        return ResponseEntity.ok(service.getByCompanyId(companyId));
    }

    @PutMapping("/company/{companyId}/logo/{tipo}")
    public ResponseEntity<CompanyParameters> uploadLogo(
            @PathVariable String companyId,
            @PathVariable LogoType tipo,
            @RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok(service.updateLogoBase64(companyId, tipo, file));
    }
}
