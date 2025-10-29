package com.cumpleanos.notification.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiconfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Notificacion Service")
                                .description("Documentacion Notificacion Email API")
                                .version("1.0")
                                .contact(new Contact().email("luischumi.9@gmail.com").name("Luis Chumi").url("https://github.com/LChumi"))
                                .termsOfService("Terminos y condiciones aplicadas")
                                .license(
                                        new License()
                                                .name("Apache license Version 2.0")
                                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                                )
                )
                .servers(List.of(
                        new Server().url("http://127.0.0.1:8083").description("Servidor de desarrollo"),
                        new Server().url("https://apis.cumpleanos.com.ec").description("Servidor de producción")
                ));
    }
}
