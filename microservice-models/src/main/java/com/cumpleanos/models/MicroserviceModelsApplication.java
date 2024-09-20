package com.cumpleanos.models;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = "core.cumpleanos.models")
public class MicroserviceModelsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceModelsApplication.class, args);
	}

}
