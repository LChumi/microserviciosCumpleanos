package com.cumpleanos.ecommerce.service.http;

import jakarta.ws.rs.core.MediaType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(name = "wooCommerceMediaClient", url = "${woocommerce.url}/wp-json/wp/v2")
public interface WooCommerceMediaClient {

    @PostMapping(value = "/media", consumes = MediaType.MULTIPART_FORM_DATA)
    Map<String, Object> uploadImage(
            @RequestPart("file") MultipartFile file,
            @RequestParam("consumer_key") String consumerKey,
            @RequestParam("consumer_secret") String consumerSecret
    );
}
