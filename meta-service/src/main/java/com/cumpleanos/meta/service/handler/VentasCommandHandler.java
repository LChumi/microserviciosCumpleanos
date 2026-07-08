package com.cumpleanos.meta.service.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VentasCommandHandler implements TelegramCommandHandler {

    @Override
    public String command() {
        return "/ventas";
    }

    //private final VentasClient ventasClient; // FeignClient hacia el otro microservicio

    @Override
    public String handle(Long chatId, String texto) {
        //VentasResumen resumen = ventasClient.obtenerResumenHoy();
        return """
                📊 Ventas de hoy

                Cantidad: %s
                Total: $%s
                """
                .formatted(
                        0,//resumen.cantidad(),
                       0); //resumen.total());
    }
}
