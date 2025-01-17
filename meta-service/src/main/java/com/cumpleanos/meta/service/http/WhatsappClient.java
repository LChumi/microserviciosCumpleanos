package com.cumpleanos.meta.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "whatsappClient", url = "https://graph.facebook.com/v18.0")
public interface WhatsappClient {

    @PostMapping("/{phoneNumberId}/messages")
    void sendMessage(@RequestHeader("Authorization") String authorization,
                     @RequestBody Object message,
                     @PathVariable("phoneNumberId") String phoneNumberId);
}
