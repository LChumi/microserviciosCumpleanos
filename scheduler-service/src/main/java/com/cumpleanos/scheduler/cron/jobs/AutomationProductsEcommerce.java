package com.cumpleanos.scheduler.cron.jobs;

import com.cumpleanos.common.records.StockEcommerceVDTO;
import com.cumpleanos.scheduler.service.http.AssistClient;
import com.cumpleanos.scheduler.service.implementation.ProductUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AutomationProductsEcommerce {

    private final AssistClient assistClient;
    private final ProductUpdateService productUpdateService;

    @Scheduled(cron = "${cron.sync_products}")
    public void run() {
        log.info("Iniciando proceso de actualización de productos");

        List<StockEcommerceVDTO> productos = assistClient.findAll().getBody();
        if (productos == null || productos.isEmpty()) {
            log.info("Lista de productos vacía");
            return;
        }

        for (StockEcommerceVDTO p : productos) {
            productUpdateService.updateProducts(p);
        }

        log.info("Proceso de actualización finalizado");
    }

}
