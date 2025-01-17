package com.cumpleanos.meta;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition( info =
		@Info(title = "Meta-service", description = "Docuemntacion Meta Api v1.0")
)
@EnableFeignClients
public class MetaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaServiceApplication.class, args);
	}

}
