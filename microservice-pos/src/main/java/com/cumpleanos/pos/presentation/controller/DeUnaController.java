package com.cumpleanos.pos.presentation.controller;

import com.cumpleanos.core.models.exception.ApiResponse;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentResponse;
import com.cumpleanos.pos.service.interfaces.IDeUnaSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pos")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DeUnaController {

    private final IDeUnaSyncService deunaSyncService;

    @GetMapping("/generar-pago/{usrLiq}/{empresa}")
    public ResponseEntity<?> test(@PathVariable Long usrLiq, @PathVariable Long empresa) {
        ApiResponse<PaymentResponse> response = deunaSyncService.procesarPago(usrLiq,empresa);
        if (response.getError() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getError());
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(response.getData());
        }
    }
}