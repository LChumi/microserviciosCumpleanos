package com.cumpleanos.assist.presentation.controller;

import com.cumpleanos.assist.service.interfaces.IDmovprodConService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assist")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DmovprodConController {

    private final IDmovprodConService service;
}
