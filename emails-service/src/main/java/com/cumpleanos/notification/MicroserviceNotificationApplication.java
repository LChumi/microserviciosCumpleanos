package com.cumpleanos.notification;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition
@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceNotificationApplication.class, args);
	}

}
