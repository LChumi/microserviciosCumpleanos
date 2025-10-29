package com.cumpleanos.scheduler.cron.jobs;

import com.cumpleanos.common.records.StockEcommerceVDTO;
import com.cumpleanos.scheduler.service.http.AssistClient;
import com.cumpleanos.scheduler.service.implementation.ProductUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@PropertySource("classpath:cron.properties")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AutomationProductsEcommerce {

    private final AssistClient assistClient;
    private final ProductUpdateService productUpdateService;

    @Scheduled(cron = "${cron.sync_products}")
    public void run() {
        log.info("Iniciando proceso de actualizacion de productos de Sistema en Ecommerce");
        List<StockEcommerceVDTO> productos = assistClient.findAll().getBody();

        if (productos == null || productos.isEmpty()) {
            log.info("Lista de productos vacias");
            return;
        }

        log.info("Iniciando actualizacion de {} productos en Ecommerce", productos.size());

        List<CompletableFuture<Void>> futures = productos.stream()
                .map(productUpdateService::updateProducts)
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        log.info("Proceso de actualizacion finalizado");
    }
}
