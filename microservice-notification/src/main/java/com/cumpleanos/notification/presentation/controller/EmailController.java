package com.cumpleanos.notification.presentation.controller;

import com.cumpleanos.common.records.EmailRecord;
import com.cumpleanos.notification.service.interfaces.IEmailService;
import com.fasterxml.jackson.core.type.TypeReference;
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

    @PostMapping(value = "/enviar/adjuntos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> enviarMailAdjuntos(
            @RequestPart("email") MultipartFile emailJson,
            @RequestPart("attachments") List<MultipartFile> files,
            @RequestPart("filenames") MultipartFile filenamesJson
    ) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        EmailRecord email = mapper.readValue(emailJson.getInputStream(), EmailRecord.class);
        List<String> filenames = mapper.readValue(filenamesJson.getInputStream(), new TypeReference<>() {});

        if (files.size() != filenames.size()) {
            return ResponseEntity.badRequest().body("Cantidad de archivos y nombres no coinciden");
        }

        Map<String, byte[]> attachments = new HashMap<>();
        for(int i = 0; i < files.size(); i++) {
            attachments.put(filenames.get(i), files.get(i).getBytes());
        }

        emailService.sendEmailWithHtmlAndAttachments(email, attachments);
        return ResponseEntity.ok("Correo enviado correctamente");
    }

}
