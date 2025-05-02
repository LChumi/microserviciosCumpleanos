package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.service.http.Images36Client;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ImagesController {

    private final Images36Client service;

    @GetMapping("/images/logo/{empresaId}")
    public ResponseEntity<Resource> getImageLogo(@PathVariable String empresaId) {
        return buildImageResponse(service.getImageLogo(empresaId), "logo");
    }

    @GetMapping("/images/usuario/{usrid}")
    public ResponseEntity<Resource> getImageUser(@PathVariable String usrid) {
        return buildImageResponse(service.getImageUser(usrid), "usuario");
    }

    @GetMapping("/images/producto/{imageName}")
    public ResponseEntity<Resource> getImageProduct(@PathVariable String imageName) {
        return buildImageResponse(service.getImageProduct(imageName), "producto");
    }


    private String getExtensionFromContentType(MediaType mediaType) {
        if (mediaType == null) return "img";
        return switch (mediaType.toString()) {
            case "image/png" -> "png";
            case "image/jpeg" -> "jpg";
            case "image/webp" -> "webp";
            default -> "img";
        };
    }

    private ResponseEntity<Resource> buildImageResponse(ResponseEntity<byte[]> response, String defaultName) {
        if (response.getBody() == null || response.getBody().length == 0) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayResource resource = new ByteArrayResource(response.getBody());

        return ResponseEntity.status(response.getStatusCode())
                .contentType(Objects.requireNonNull(response.getHeaders().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + defaultName + "." + getExtensionFromContentType(response.getHeaders().getContentType()) + "\"")
                .header(HttpHeaders.CACHE_CONTROL, "public, max-age=86400")
                .body(resource);
    }
}
