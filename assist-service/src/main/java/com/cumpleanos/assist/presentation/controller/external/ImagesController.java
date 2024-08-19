package com.cumpleanos.assist.presentation.controller.external;

import com.cumpleanos.assist.service.http.Images36Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Imagenes", description = "Documentacion API para obtener imagenes ")
public class ImagesController {

    private final Images36Client service;

    @Operation(summary = "Obtener imagenes logos de la empresa")
    @Parameter(name = "empresaId", description = "Codigo de Empresa")
    @GetMapping("/images/logo/{empresaId}")
    public ResponseEntity<byte[]> getImageLogo(@PathVariable String empresaId) {
        return buildImageResponse(service.getImageLogo(empresaId));
    }

    @Operation(summary = "Obtener imagenes de usuarios")
    @Parameter(name = "usrid", description = "Codigo de usuario")
    @GetMapping("/images/usuario/{usrid}")
    public ResponseEntity<byte[]> getImageUser(@PathVariable String usrid) {
        return buildImageResponse(service.getImageUser(usrid));
    }

    @Operation(summary = "Obtener imagenes de productos")
    @Parameter(name = "imageName", description = "Barra de Producto")
    @GetMapping("/images/producto/{imageName}")
    public ResponseEntity<byte[]> getImageProduct(@PathVariable String imageName) {
        return buildImageResponse(service.getImageProduct(imageName));
    }

    @Operation(summary = "Obtener imagenes de productos bunna")
    @Parameter(name = "sku", description = "Barra de Producto")
    @GetMapping("/images/producto/{sku}/bunna")
    public ResponseEntity<byte[]> getImageProductBunna(@PathVariable String sku) {
        return buildImageResponse(service.getImageProductBunna(sku));
    }

    @Operation(summary = "Obtener imagenes de productos interiori")
    @Parameter(name = "sku", description = "Barra de Producto")
    @GetMapping("/images/producto/{sku}/interiori")
    public ResponseEntity<byte[]> getImageProductInteriori(@PathVariable String sku) {
        return buildImageResponse(service.getImageProductInteriori(sku));
    }

    @Operation(summary = "Valida si existe imagen en el producto")
    @Parameter(name = "imageName", description = "Barra de Producto")
    @GetMapping("/images/producto/exist/{imageName}")
    public ResponseEntity<Short> imageExist(@PathVariable String imageName) {
        return service.getImageProductExist(imageName);
    }

    private ResponseEntity<byte[]> buildImageResponse(ResponseEntity<byte[]> response) {

        byte[] body = response.getBody();
        if (body == null || body.length == 0) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();

        MediaType ct = response.getHeaders().getContentType();

        // 🔥 Telegram + browsers safe
        headers.setContentType(
                (ct != null && ct.getType().equals("image"))
                        ? ct
                        : MediaType.IMAGE_JPEG
        );

        headers.setContentLength(body.length);

        // CDN CACHE (edge friendly)
        headers.setCacheControl(CacheControl.maxAge(6, TimeUnit.HOURS).cachePublic());

        // opcional: ayuda a proxies/CDN

        String etag = generateETag(body);
        if (etag != null) {
            headers.setETag(etag);
        }

        headers.setPragma("cache");
        headers.setExpires(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);

        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    private String generateETag(byte[] body) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(body);
            return "\"" + Base64.getEncoder().encodeToString(digest) + "\"";
        } catch (Exception e) {
            return null;
        }
    }
}