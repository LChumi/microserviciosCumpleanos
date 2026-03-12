package com.cumpleanos.ws;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class WsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsServiceApplication.class, args);
	}

}