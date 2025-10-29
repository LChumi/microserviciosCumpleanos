package com.cumpleanos.assist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EntityScan(basePackages = {"com.cumpleanos.core.models", "com.cumpleanos.assist.persistence"})
@EnableFeignClients
public class AssistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssistServiceApplication.class, args);
	}

}
