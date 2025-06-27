package com.cumpleanos.reccomprobantes.presentation.controller;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.reccomprobantes.service.implementation.CoordinatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("recp")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FilesProcessingController {

    private final CoordinatorService service;

    @PostMapping("/file")
    public ResponseEntity<ServiceResponse> uploadFile(@RequestParam("file") MultipartFile file,
                                                      @RequestParam("email") String email) throws IOException {
        Object result = service.processFile(file, email);

        return service.getServiceResponseResponseEntity(result);

    }

    @PostMapping("/string")
    public ResponseEntity<ServiceResponse> processString(@RequestBody String string,
                                           @RequestParam("email") String email) throws IOException {
        Object result = service.processString(string, email);

        return service.getServiceResponseResponseEntity(result);
    }

}