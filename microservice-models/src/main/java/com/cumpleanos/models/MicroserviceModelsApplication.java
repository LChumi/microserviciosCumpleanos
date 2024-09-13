package com.cumpleanos.models;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Modelos Principales", description = "Documentation Modelos Principales API v1.0", contact = @Contact(name = "Luis Chumi", email = "lchumi@cumpleanos.com.ec"))
)
public class MicroserviceModelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceModelsApplication.class, args);
	}

}
