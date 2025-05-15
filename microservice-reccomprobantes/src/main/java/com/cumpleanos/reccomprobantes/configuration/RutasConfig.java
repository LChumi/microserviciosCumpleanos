package com.cumpleanos.reccomprobantes.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:filepaths.properties")
@Getter
public class RutasConfig {

    @Value("${route.file.clientesW}")
    private String rutaCliente;
}
