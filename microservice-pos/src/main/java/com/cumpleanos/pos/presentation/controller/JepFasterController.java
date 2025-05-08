package com.cumpleanos.pos.presentation.controller;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.pos.persistence.api.jep.JepRequestQr;
import com.cumpleanos.pos.persistence.api.jep.JepResponseQr;
import com.cumpleanos.pos.persistence.api.jep.NotificacionJep;
import com.cumpleanos.pos.service.implementation.JepFasterClientService;
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
    private final JepFasterClientService jepFasterClientService;

    @PostMapping("/servicios/notificacioncomercio/notifyPayment")
    public ResponseEntity<ServiceResponse> notifyPayment(NotificacionJep notificacion) {
        service.procesarPago(notificacion);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/jep-faster/qr")
    public ResponseEntity<JepResponseQr> generarQR(JepRequestQr jep) {
        JepResponseQr response = jepFasterClientService.getQR(jep).getData();
        return ResponseEntity.ok(response);

    }
}
