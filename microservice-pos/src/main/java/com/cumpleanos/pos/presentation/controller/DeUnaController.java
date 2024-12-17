package com.cumpleanos.pos.presentation.controller;

import com.cumpleanos.core.models.exception.ApiResponse;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentRequest;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentResponse;
import com.cumpleanos.pos.service.implementation.DeunaPaymentClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pos")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class DeUnaController {

    private final DeunaPaymentClientService deunaService;

    @PostMapping("/procesar/pago")
    public ResponseEntity<?> procesarPago(@RequestBody PaymentRequest paymentRequest) {
        ApiResponse<PaymentResponse> response = deunaService.getPayment(paymentRequest);
        if (response.getError() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getError());
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).body(response.getData());
        }
    }
}
