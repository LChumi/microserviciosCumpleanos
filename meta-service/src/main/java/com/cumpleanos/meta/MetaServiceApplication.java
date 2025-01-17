package com.cumpleanos.meta;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition( info =
		@Info(title = "Meta-service", description = "Docuemntacion Meta Api v1.0")
)
public class MetaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaServiceApplication.class, args);
	}

}
