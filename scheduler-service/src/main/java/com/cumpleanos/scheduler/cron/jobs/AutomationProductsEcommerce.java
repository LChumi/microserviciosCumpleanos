package com.cumpleanos.scheduler.cron.jobs;

import com.cumpleanos.common.records.StockEcommerceVDTO;
import com.cumpleanos.scheduler.service.http.AssistClient;
import com.cumpleanos.scheduler.service.implementation.ProductUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AutomationProductsEcommerce {

    private static final AtomicBoolean running = new AtomicBoolean(false);

    private final AssistClient assistClient;
    private final ProductUpdateService productUpdateService;

    @Scheduled(cron = "${cron.sync_products}")
    public void run() {

        if (!running.compareAndSet(false, true)) {
            log.warn("Sync ya en ejecucion, se omite esta tarea");
            return;
        }

        try {
            log.info("Iniciando sincronizacion de productos");

            List<StockEcommerceVDTO> productos = Optional.ofNullable(assistClient.findAll().getBody())
                    .orElse(Collections.emptyList());

            if (productos.isEmpty()) {
                log.info("Lista de productos vacia, omitiendo...");
            } else {
                productos.forEach(productUpdateService::updateProducts);
                log.info("Sincronización finalizada, total de productos");
            }
        } catch (Exception e) {
            log.error("Error en sincronizacion de productos", e);
        } finally {
            //Desbloquear
            running.set(false);
            log.info("Proceso de actualización finalizado");
        }
    }

}
