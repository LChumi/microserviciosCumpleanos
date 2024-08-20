package com.cumpleanos.reccomprobantes.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class OpenAPIConfiguration {
    @Bean
    public OpenAPI expenseApi(){
        return new OpenAPI()
                .info(new Info().title("Cumpleanos Caja POS API")
                        .description("Api importadora cumpleaños Recepcion de Comprobantes")
                        .version("1.0")
                        .license(new License().name("Apache License Version 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                );// opcional externalDosc para demas licencias
    }
}
