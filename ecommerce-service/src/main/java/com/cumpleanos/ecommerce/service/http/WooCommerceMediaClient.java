package com.cumpleanos.ecommerce.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(name = "wooCommerceMediaClient", url = "${wordpress.url}")
public interface WooCommerceMediaClient {

    @PostMapping(value = "/wp-json/wp/v2/media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Map<String, Object> uploadImage(
            @RequestHeader("Authorization") String authorization,  // Aquí el token JWT
            @RequestPart("file") MultipartFile file
    );
}