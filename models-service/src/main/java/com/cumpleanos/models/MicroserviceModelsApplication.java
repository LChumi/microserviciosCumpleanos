package com.cumpleanos.models;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EntityScan(basePackages = "com.cumpleanos.core.models")
public class MicroserviceModelsApplication {

	static void main(String[] args) {
		SpringApplication.run(MicroserviceModelsApplication.class, args);
	}

}
