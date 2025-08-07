package com.cumpleanos.pos.presentation.controller;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.pos.persistence.api.jep.JepResponseQr;
import com.cumpleanos.pos.persistence.api.jep.NotificacionJep;
import com.cumpleanos.pos.service.interfaces.IJepFasterSyncService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pos")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Tag(name = "JepFaster", description = "Documentacion API JepFaster")
public class JepFasterController {

    private final IJepFasterSyncService service;

    @Operation(summary = "Webhook JepFaster", description = "Notifica cuando el pago se realizo correctamente", tags = {"JepFaster"}, responses = {
            @ApiResponse(responseCode = "200", description = "Retorno un mensaje del procesos y estado si cumple con la informacion del sistema actualiza informacion en el sistema")
    })
    @PostMapping("/servicios/notificacioncomercio/notifyPayment")
    public ResponseEntity<ServiceResponse> notifyPayment(@RequestBody NotificacionJep notification) {
        service.procesarPago(notification);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Generar QR", description = "Genera un Qr con la informacion del pago del sistema a JepFaster", tags = {"JepFaster"})
    @Parameters({
            @Parameter(name = "UsrLiq", description = "Codigo de Usuario Liquida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/jep-faster/qr/{usrLiq}/{empresa}")
    public ResponseEntity<JepResponseQr> generarQR(@PathVariable Long usrLiq, @PathVariable Long empresa) {
        JepResponseQr response = service.generarQR(usrLiq, empresa, true);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Validar Pago", description = "Loop que verifica si el webhook actualizo la informacion en el sistema", tags = {"JepFaster"})
    @Parameters({
            @Parameter(name = "UsrLiq", description = "Codigo de Usuario Liquida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/jep-faster/validar-pago/{usrLiq}/{empresa}")
    public ResponseEntity<ServiceResponse> validarPago(@PathVariable Long usrLiq, @PathVariable Long empresa) {
        ServiceResponse response = service.validarPago(usrLiq, empresa);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Verifica Pago", description = "Consulta en el sistema si el Recibo fue aprobado")
    @Parameters({
            @Parameter(name = "UsrLiq", description = "Codigo de Usuario Liquida"),
            @Parameter(name = "empresa", description = "Codigo de empresa")
    })
    @GetMapping("/jep-faster/verificar-pago/{usrLiq}/{empresa}")
    public ResponseEntity<ServiceResponse> verificarPagoExistente(@PathVariable Long usrLiq, @PathVariable Long empresa) {
        ServiceResponse response = service.verificarPago(usrLiq, empresa);
        return ResponseEntity.ok(response);
    }
}
