package com.cumpleanos.reccomprobantes;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(info =
@Info(title = "Recepcion Comprobantes SRI", version = "${springframework.version}", description = "Documentacion API Recepcion v1.0")
)
public class MicroserviceReccomprobantesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceReccomprobantesApplication.class, args);
	}

}
