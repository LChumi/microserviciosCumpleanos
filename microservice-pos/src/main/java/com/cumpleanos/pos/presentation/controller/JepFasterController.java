package com.cumpleanos.pos.presentation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pos")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JepFasterController {
}
