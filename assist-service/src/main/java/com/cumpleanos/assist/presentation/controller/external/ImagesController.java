package com.cumpleanos.assist.presentation.controller.external;

import com.cumpleanos.assist.service.http.Images36Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Imagenes", description = "Documentacion API para obtener imagenes ")
public class ImagesController {

    private final Images36Client service;

    @Operation(summary = "Obtener imagenes logos de la empresa")
    @Parameter(name = "empresaId", description = "Codigo de Empresa")
    @GetMapping("/images/logo/{empresaId}")
    public ResponseEntity<Resource> getImageLogo(@PathVariable String empresaId) {
        return buildImageResponse(service.getImageLogo(empresaId), "logo");
    }

    @Operation(summary = "Obtener imagenes de usuarios")
    @Parameter(name = "usrid", description = "Codigo de usuario")
    @GetMapping("/images/usuario/{usrid}")
    public ResponseEntity<Resource> getImageUser(@PathVariable String usrid) {
        return buildImageResponse(service.getImageUser(usrid), "usuario");
    }

    @Operation(summary = "Obtener imagenes de productos")
    @Parameter(name = "imageName", description = "Barra de Producto")
    @GetMapping("/images/producto/{imageName}")
    public ResponseEntity<Resource> getImageProduct(@PathVariable String imageName) {
        return buildImageResponse(service.getImageProduct(imageName), "producto");
    }

    @Operation(summary = "Obtener imagenes de productos bunna")
    @Parameter(name = "sku", description = "Barra de Producto")
    @GetMapping("/images/producto/{sku}/bunna")
    public ResponseEntity<Resource> getImageProductBunna(@PathVariable String sku) {
        return buildImageResponse(service.getImageProductBunna(sku), "producto");
    }

    @Operation(summary = "Valida si existe imagen en el producto")
    @Parameter(name = "imageName", description = "Barra de Producto")
    @GetMapping("/images/producto/exist/{imageName}")
    public ResponseEntity<Short> imageExist(@PathVariable String imageName) {
        return service.getImageProductExist(imageName);
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