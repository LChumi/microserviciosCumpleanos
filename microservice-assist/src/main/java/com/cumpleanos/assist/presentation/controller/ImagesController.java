package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.service.implementation.Images36ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ImagesController {

    private final Images36ServiceImpl service;

    @GetMapping("/images/logo/{empresaId}")
    public ResponseEntity<Resource> getImageLogo(@PathVariable String empresaId ){
        return ResponseEntity.ok(service.getLogoImage(empresaId));
    }

    @GetMapping("/images/usuario/{usrid}")
    public ResponseEntity<Resource> getImageUser(@PathVariable String usrid){
        return ResponseEntity.ok(service.getUserImage(usrid));
    }

    @GetMapping("/images/producto/{imageName}")
    public ResponseEntity<Resource> getImageProduct(@PathVariable String imageName){
        return ResponseEntity.ok(service.getProductImage(imageName));
    }
}
