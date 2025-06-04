package com.cumpleanos.notification.presentation.controller;

import com.cumpleanos.common.records.EmailRecord;
import com.cumpleanos.notification.service.interfaces.IEmailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Validated
public class EmailController {

    private final IEmailService emailService;

    @PostMapping("/enviar/html")
    public ResponseEntity<?> enviarMailHtml(@Valid  @RequestBody EmailRecord email){
        emailService.sendEmailWithHtmlAndAttachments(email, Collections.emptyMap());
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/enviar/adjunto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> enviarMailAdjunto(@RequestPart("file") MultipartFile file,
                                                    @RequestPart("filename") String filename,
                                                    @RequestPart("email") MultipartFile emailFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        EmailRecord email = objectMapper.readValue(emailFile.getBytes(), EmailRecord.class);
        if (file.isEmpty() || filename == null || filename.isBlank()) {
            throw new IllegalArgumentException("El archivo y su nombre son obligatorios.");
        }

        emailService.sendMailAttach(email, filename, file.getBytes());
        return ResponseEntity.ok("Correo enviado correctamente");
    }

    @PostMapping(value = "/enviar/adjuntos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> enviarMailAdjuntos(
            @RequestPart("email") MultipartFile emailFile,
            @RequestPart("attachments") List<MultipartFile> files,
            @RequestPart("filenames") List<String> filenames
    ) throws IOException {
        if (files.size() != filenames.size()) {
            return ResponseEntity.badRequest().body("Cantidad de archivos y nombres no coinciden");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        EmailRecord email = objectMapper.readValue(emailFile.getBytes(), EmailRecord.class);

        Map<String, byte[]> attachments = new HashMap<>();
        for(int i = 0; i < files.size(); i++) {
            attachments.put(filenames.get(i), files.get(i).getBytes());
        }

        emailService.sendEmailWithHtmlAndAttachments(email, attachments);
        return ResponseEntity.ok("Correo enviado correctamente");
    }

}
