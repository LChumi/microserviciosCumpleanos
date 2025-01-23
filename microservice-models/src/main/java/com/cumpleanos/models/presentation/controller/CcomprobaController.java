package com.cumpleanos.models.presentation.controller;

import com.cumpleanos.models.service.interfaces.ICcomprobaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CcomprobaController {
    private final ICcomprobaService service;
}
