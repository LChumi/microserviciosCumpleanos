package com.cumpleanos.pos.presentation.controller;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.pos.persistence.api.jep.NotificacionJep;
import com.cumpleanos.pos.service.interfaces.IJepFasterSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pos")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JepFasterController {

    private final IJepFasterSyncService service;

    @PostMapping("/servicios/notificacioncomercio/notifyPayment")
    ResponseEntity<ServiceResponse> notifyPayment(NotificacionJep notificacion) {
        service.procesarPago(notificacion);
        return ResponseEntity.ok().build();
    }
}
