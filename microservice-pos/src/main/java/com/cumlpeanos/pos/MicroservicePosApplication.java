package com.cumlpeanos.pos;

import com.cumlpeanos.pos.config.OpenAPIConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import(OpenAPIConfiguration.class)
public class MicroservicePosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicePosApplication.class, args);
	}

}
