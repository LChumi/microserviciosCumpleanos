package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.models.service.interfaces.ICcomprobaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "Ccomproba", description = "Documentacion API Ccomproba")
public class CcomprobaController {
    private final ICcomprobaService service;
}
