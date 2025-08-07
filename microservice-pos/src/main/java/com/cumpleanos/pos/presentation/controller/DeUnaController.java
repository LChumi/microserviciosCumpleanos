package com.cumpleanos.pos.presentation.controller;

import com.cumpleanos.common.exception.ApiResponse;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.pos.persistence.api.deuna.infoPayments.InfoResponse;
import com.cumpleanos.pos.persistence.api.deuna.payments.PaymentResponse;
import com.cumpleanos.pos.service.interfaces.IDeUnaSyncService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pos")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "DeUna", description = "Docuemtacion API DeUna Pagos")
public class DeUnaController {

    private final IDeUnaSyncService deunaSyncService;

    @Operation(summary = "Generar QR", description = "Genera el Qr para poder realizar el pago con la Api en el punto de venta que se realiza la transaccion", tags = {"DeUna"})
    @Parameters({
            @Parameter(name = "UsrLiq", description = "Codigo de Usuario Liquida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/generar-pago/{usrLiq}/{empresa}")
    public ResponseEntity<?> test(@PathVariable Long usrLiq, @PathVariable Long empresa) {
        ApiResponse<PaymentResponse> response = deunaSyncService.procesarPago(usrLiq, empresa);
        if (response.getError() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getError());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(response.getData());
        }
    }

    @Operation(summary = "Validar Pago", description = "Valida si el pago con el Qr ya fue procesado actualiza en el sistema", tags = {"DeUna"})
    @Parameters({
            @Parameter(name = "UsrLiq", description = "Codigo de Usuario Liquida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/validar-pago/{usrlLiq}/{empresa}")
    public ResponseEntity<InfoResponse> validarPago(@PathVariable Long usrlLiq, @PathVariable Long empresa) {
        InfoResponse response = deunaSyncService.procesarInfoPayment(usrlLiq, empresa);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Validar Pago", description = "Valida si el pago con el Qr ya fue procesado actualiza en el sistema", tags = {"DeUna"})
    @Parameters({
            @Parameter(name = "UsrLiq", description = "Codigo de Usuario Liquida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/verificar-pago-existente/{usrLiq}/{empresa}")
    public ResponseEntity<ServiceResponse> verificarPagoExistente(@PathVariable Long usrLiq, @PathVariable Long empresa) {
        ServiceResponse response = deunaSyncService.procesarInfoRecibo(usrLiq, empresa);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
