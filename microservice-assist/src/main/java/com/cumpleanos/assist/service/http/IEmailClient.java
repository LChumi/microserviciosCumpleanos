package com.cumpleanos.assist.service.http;

import com.cumpleanos.core.models.dto.EmailRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface IEmailClient {

    @PostMapping("/email/enviar/text")
    ResponseEntity<?> enviarEmail(@RequestBody EmailRecord email);

}