package com.cumpleanos.notification.presentation.controller;

import com.cumpleanos.notification.service.interfaces.IEmailService;
import com.cumpleanos.notification.utils.record.EmailRecord;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSendException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
@Validated
public class EmailController {

    private final IEmailService emailService;

    @PostMapping("/enviar/html")
    public ResponseEntity<?> enviarMailHtml(@Valid  @RequestBody EmailRecord email){
        emailService.sendMailHtml(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/enviar/text")
    public ResponseEntity<?> enviarMail(@Valid @RequestBody EmailRecord email){
        emailService.sendMail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/enviar/adjunto")
    public ResponseEntity<String> enviarMailAdjunto(@Valid @RequestBody EmailRecord email,
                                                    @RequestParam("file") MultipartFile file,
                                                    @RequestParam("filename") String filename) throws IOException {
        if (file.isEmpty() || filename == null || filename.isBlank()) {
            throw new IllegalArgumentException("El archivo y su nombre son obligatorios.");
        }

        emailService.sendMailAttach(email, filename, file.getBytes());
        return ResponseEntity.ok("Correo enviado correctamente");
    }

}
