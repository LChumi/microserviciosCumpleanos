package com.cumpleanos.reccomprobantes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceReccomprobantesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceReccomprobantesApplication.class, args);
	}

}
