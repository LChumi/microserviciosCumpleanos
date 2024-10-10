package com.cumpleanos.reccomprobantes.presentation.controller;

import com.cumpleanos.reccomprobantes.service.implementation.CoordinatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/recp")
@RequiredArgsConstructor
public class FilesProcessingController {

    private final CoordinatorService service;

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Object result = service.processFile(file);
        return ResponseEntity.ok(result);

    }

    @PostMapping("/string")
    public ResponseEntity<?> processString(@RequestBody String string) {
        Object result = service.processString(string);
        return ResponseEntity.ok(result);
    }
}
