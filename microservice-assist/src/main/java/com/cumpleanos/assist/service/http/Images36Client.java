package com.cumpleanos.assist.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "images-service", url = "http://192.168.112.36:7569/images")
public interface Images36Client {

    @GetMapping("/producto/{imageName}")
    ResponseEntity<Resource> getImageProduct(@PathVariable String imageName);

    @GetMapping("/usuario/{usrid}")
    ResponseEntity<Resource> getImageUser(@PathVariable String usrid);

    @GetMapping("/logo/{empresaId}")
    ResponseEntity<Resource> getImageLogo(@PathVariable String empresaId );
}
