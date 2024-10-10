package com.cumpleanos.models;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = "com.cumpleanos.core.models")
@OpenAPIDefinition(info =
@Info(title = "API Modelos ", description = "Documentacion API Modelos v1.0")
)
public class MicroserviceModelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceModelsApplication.class, args);
	}

}
