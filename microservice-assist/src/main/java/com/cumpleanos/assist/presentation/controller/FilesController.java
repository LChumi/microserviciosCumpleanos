package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.service.implementation.files.FilesServicesImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class FilesController {

    private final FilesServicesImpl filesServices;

    @PostMapping("/excel/solicitud")
    public ResponseEntity<List<ProductImportTransformer>> importExcel(@RequestParam("file") MultipartFile file,
                                                                      @RequestParam("empresa") Long empresa) throws IOException {
        List<ProductImportTransformer> items = filesServices.readExcelFile(file, empresa);
        return ResponseEntity.ok(items);
    }
}
