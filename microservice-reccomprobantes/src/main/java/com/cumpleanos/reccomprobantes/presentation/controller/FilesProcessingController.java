package com.cumpleanos.reccomprobantes.presentation.controller;

import com.cumpleanos.reccomprobantes.service.implementation.CoordinatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("recp")
@RequiredArgsConstructor
public class FilesProcessingController {

    private final CoordinatorService service;

    @PostMapping("/file")
    public ResponseEntity<Boolean> uploadFile(@RequestParam("file") MultipartFile file,
                                        @RequestParam("email") String email) throws IOException {
        Object result = service.processFile(file, email);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("/string")
    public ResponseEntity<Boolean> processString(@RequestBody String string,
                                           @RequestParam("email") String email) throws IOException {
        Object result = service.processString(string, email);
        if (result == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(false);
        }
        return ResponseEntity.ok(true);
    }
}
