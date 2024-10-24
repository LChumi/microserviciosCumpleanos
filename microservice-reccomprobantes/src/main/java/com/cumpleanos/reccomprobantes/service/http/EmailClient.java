package com.cumpleanos.reccomprobantes.service.http;

import com.cumpleanos.core.models.dto.EmailRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface EmailClient {

    //TODO servicio que viene del controlador EmailController
    @PostMapping("/email/enviar")
    ResponseEntity<?> enviar(@RequestBody EmailRecord email);
}