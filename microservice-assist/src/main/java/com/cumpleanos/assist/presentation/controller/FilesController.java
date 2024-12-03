package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.service.implementation.files.FilesServicesImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RestController("assist")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class FilesController {

    private final FilesServicesImpl filesServices;

    @PostMapping("/excel/import")
    public ResponseEntity<List<ProductImportTransformer>> importExcel(@RequestParam("file") MultipartFile file,
                                                                      @RequestParam("empresa") Long empresa) {
        try {
            List<ProductImportTransformer> items = filesServices.readExcelFile(file, empresa);
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }
}
