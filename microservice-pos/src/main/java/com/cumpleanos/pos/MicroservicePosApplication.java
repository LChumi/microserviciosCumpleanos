package com.cumpleanos.pos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition( info =
@Info(title = "Caja POS", description = "Documentacion Caja POS API v1.0")
)
public class MicroservicePosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePosApplication.class, args);
	}

}
