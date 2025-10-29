package com.cumpleanos.ws.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Api WebFlux Notificaciones")
                                .description("Docuemntacion WebFlux de Notificaciones")
                                .version("0.01")
                                .contact(new Contact().email("luischumi.9@gmail.com").name("Luis Chumi").url("https://github.com/LChumi"))
                                .termsOfService("Terminos y condiciones aplicadas")
                                .license(new License().name("Apache License Version 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                )
                .servers(List.of(
                        new Server().url("http://127.0.0.1:8082").description("Servidor de desarrollo"),
                        new Server().url("https://api.bunnashop.com").description("Servidor de producción")
                ));

    }
}