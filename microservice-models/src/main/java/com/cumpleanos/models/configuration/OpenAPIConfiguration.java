package com.cumpleanos.models.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
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
                                .title("API Modelos oracle")
                                .description("Documentacion API de modelos de Oracle")
                                .version("1.0")
                                .termsOfService("Terminos y condiciones aplicadas")
                                .license(
                                        new License()
                                                .name("Apache License Version 2.0")
                                                .url("https://www.apache.org/licenses/LICENSE-2.0")
                                )
                ).servers(List.of(
                        new Server().url("http://localhost:9091").description("Servidor de desarrollo"),
                        new Server().url("https://api.bunnashop.com").description("Servidor de producción")
                ));
    }

    @Bean
    public List<GroupedOpenApi> groupedOpenApis() {
        List<String> modules = List.of(
                "acceso",
                "almacen",
                "autcliente",
                "bodega",
                "catcliente",
                "ccomproba",
                "cliente",
                "comprobante-detalle",
                "cparamet",
                "creposicion",
                "ctipocom",
                "database",
                "dfactura",
                "dreposicion",
                "dtipodoc",
                "empleado",
                "function-oralce",
                "producto",
                "punto-venta",
                "reposicion",
                "retdato",
                "sistema",
                "sri-doc",
                "tipodoc",
                "ubicacion",
                "usrbod",
                "usuario",
                "imppartida",
                "producto-partida"
        );
        return modules.stream()
                .map(module -> GroupedOpenApi.builder()
                        .group(module)
                        .pathsToMatch("/models/" + module + "/**")
                        //.addOpenApiCustomizer(openApi -> openApi.addSecurityItem(new SecurityRequirement().addList("bearerAuth")))
                        .build()
                ).toList();
    }

}
