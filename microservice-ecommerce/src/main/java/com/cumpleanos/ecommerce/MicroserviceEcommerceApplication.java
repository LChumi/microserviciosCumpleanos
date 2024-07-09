package com.cumpleanos.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient //Servicio cliente de Eureka
@SpringBootApplication
public class MicroserviceEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEcommerceApplication.class, args);
	}

}
