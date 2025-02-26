package com.cumpleanos.mongo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition( info = @Info(
		title = "Mongo Service", description = "Documentacion Microservicio Mongo"
))
public class MongoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoServiceApplication.class, args);
	}

}
