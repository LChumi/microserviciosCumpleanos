package com.cumpleanos.models;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Department API", version = "${springdoc.version}", description = "Documentation Department API v1.0")
)
public class MicroserviceModelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceModelsApplication.class, args);
	}

}
