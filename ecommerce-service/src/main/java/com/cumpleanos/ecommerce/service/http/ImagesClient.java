package com.cumpleanos.ecommerce.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "image-service", url = "${images.server.url}")
public interface ImagesClient {

    @GetMapping("producto/{imageName}")
    ResponseEntity<Resource> getImage(@PathVariable String imageName);
}
