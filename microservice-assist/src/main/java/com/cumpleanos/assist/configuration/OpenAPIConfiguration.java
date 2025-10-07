package com.cumpleanos.assist.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("ASSIST WEB")
                                .description("Documentacion ASSIST WEB API")
                                .version("1.0")
                                .license(
                                        new License()
                                                .name("Apache license Version 2.0")
                                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                                )
                );
    }

    @Bean
    public List<GroupedOpenApi> groupedOpenApis() {
        List<String> modules = List
                .of(
                        "dmovprod",
                        "cco",
                        "importaciones",
                        "improd-trancito",
                        "acceso-rol",
                        "auth",
                        "ecommerce",
                        "images",
                        "list-ccomprobav",
                        "favoritos",
                        "index"
                );
        return modules.stream()
                .map(module -> GroupedOpenApi.builder()
                        .group(module)
                        .pathsToMatch("/assist/" + module + "/**")
                        .build()
                ).toList();
    }
}
