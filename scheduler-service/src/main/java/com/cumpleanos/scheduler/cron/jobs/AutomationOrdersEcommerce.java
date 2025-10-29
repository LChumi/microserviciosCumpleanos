package com.cumpleanos.scheduler.cron.jobs;

import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.scheduler.service.http.AssistClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource( "classpath:cron.properties")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AutomationOrdersEcommerce {

    private final AssistClient assistClient;

    @Scheduled(cron = "${cron.sync_orders}")
    public void run() {
        log.info("Iniciando proceso de actualizacion de pedidos en Sistema en Ecommerce");
        ServiceResponse response = assistClient.syncOrders().getBody();
        if (response != null){
            log.info(response.message());
        } else {
            log.warn("Ocurrio un problema al sincronizar las ordenes ");
        }

    }
}
