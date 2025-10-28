package com.cumpleanos.pos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicePosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePosApplication.class, args);
	}

}
