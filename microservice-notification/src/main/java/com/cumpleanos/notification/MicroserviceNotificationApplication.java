package com.cumpleanos.notification;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition( info =
@Info(title = "Notificacion", description = "Documentacion Notificaciones API v1.0")
)
public class MicroserviceNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceNotificationApplication.class, args);
	}

}
