package com.cumpleanos.notification.presentation.controller;

import com.cumpleanos.notification.service.interfaces.IEmailService;
import com.cumpleanos.notification.utils.record.EmailRecord;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Validated
public class EmailController {

    private final IEmailService emailService;

    @PostMapping("/enviar")
    public ResponseEntity<?> enviarMail(@Valid  @RequestBody EmailRecord email){
        emailService.sendMail(email);
        return ResponseEntity.ok().build();
    }
}
