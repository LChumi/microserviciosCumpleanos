package com.cumpleanos.pos.presentation.controller;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.pos.persistence.api.jep.JepResponseQr;
import com.cumpleanos.pos.persistence.api.jep.NotificacionJep;
import com.cumpleanos.pos.service.interfaces.IJepFasterSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pos")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JepFasterController {

    private final IJepFasterSyncService service;

    @PostMapping("/servicios/notificacioncomercio/notifyPayment")
    public ResponseEntity<ServiceResponse> notifyPayment(@RequestBody NotificacionJep notification) {
        service.procesarPago(notification);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/jep-faster/qr/{usrLiq}/{empresa}")
    public ResponseEntity<JepResponseQr> generarQR(@PathVariable Long usrLiq, @PathVariable Long empresa) {
        JepResponseQr response = service.generarQR(usrLiq, empresa, true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/jep-faster/validar-pago/{usrLiq}/{empresa}")
    public ResponseEntity<ServiceResponse> validarPago(@PathVariable Long usrLiq, @PathVariable Long empresa) {
        ServiceResponse response = service.validarPago(usrLiq, empresa);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/jep-faster/verificar-pago/{usrLiq}/{empresa}")
    public ResponseEntity<ServiceResponse> verificarPagoExistente(@PathVariable Long usrLiq, @PathVariable Long empresa) {
        ServiceResponse response = service.verificarPago(usrLiq, empresa);
        return ResponseEntity.ok(response);
    }
}
