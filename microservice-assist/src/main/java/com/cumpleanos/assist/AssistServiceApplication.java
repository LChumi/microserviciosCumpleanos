package com.cumpleanos.assist;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = "com.cumpleanos.core.models")
@OpenAPIDefinition( info = @Info(
		title = "ASSIST WEB", description = "Documentacion ASSIST WEB API v 1"))
public class AssistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssistServiceApplication.class, args);
	}

}
