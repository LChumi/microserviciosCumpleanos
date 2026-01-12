package com.cumpleanos.assist.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "images-service", url = "http://192.168.112.192:7569/images")
public interface Images36Client {

    @GetMapping(value = "/producto/{imageName}", produces = MediaType.ALL_VALUE)
    ResponseEntity<byte[]> getImageProduct(@PathVariable String imageName);

    @GetMapping(value = "/producto/{imageName}/bunna", produces = MediaType.ALL_VALUE)
     ResponseEntity<byte[]> getImageProductBunna(@PathVariable String imageName);

    @GetMapping(value = "/usuario/{usrid}", produces = MediaType.ALL_VALUE)
    ResponseEntity<byte[]> getImageUser(@PathVariable String usrid);

    @GetMapping(value = "/logo/{empresaId}", produces = MediaType.ALL_VALUE)
    ResponseEntity<byte[]> getImageLogo(@PathVariable String empresaId);

    @GetMapping("/producto/exist/{imageName}")
    ResponseEntity<Short> getImageProductExist(@PathVariable String imageName);
}
