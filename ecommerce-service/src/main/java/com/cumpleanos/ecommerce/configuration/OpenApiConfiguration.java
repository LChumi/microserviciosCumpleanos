package com.cumpleanos.ecommerce.configuration;

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
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("API Ecommerce Service")
                                .description("Documenatcion de Servicio para conectar Ecommerce API v1")
                                .version("1.0")
                                .contact(new Contact().email("luischumi.9@gmail.com").name("Luis Chumi").url("https://github.com/LChumi"))
                                .termsOfService("Terminos y condiciones aplicadas")
                                .license(new License().name("Apache License Version 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                )
                .servers(List.of(
                        new Server().url("http://127.0.0.1:8085").description("Servidor de desarrollo"),
                        new Server().url("https://apis.cumpleanos.com.ec").description("Servidor de producción")
                ));
    }
}
