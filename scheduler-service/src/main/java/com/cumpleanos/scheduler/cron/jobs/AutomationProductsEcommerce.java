package com.cumpleanos.scheduler.cron.jobs;

import com.cumpleanos.scheduler.service.http.AssistClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource("classpath:cron.properties")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AutomationProductsEcommerce {

    private final AssistClient assistClient;

    @Value("${cron.expression.everyMin}")
    private String cronExpression;

    @Scheduled(cron = "${cron.expression.everyMin}")
    public void run() {
        log.info("Cron Expression: {}", cronExpression);
        log.info("Automatization Products Ecommerce");
    }
}
