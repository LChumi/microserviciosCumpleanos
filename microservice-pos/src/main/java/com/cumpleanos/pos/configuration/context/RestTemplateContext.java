package com.cumpleanos.pos.configuration.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class RestTemplateContext {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        //Obtener los convertidores de mensajes actuales
        List<HttpMessageConverter<?>> messageConverts = restTemplate.getMessageConverters();

        // Configurar StringHttpMessageConverter con UTF-8
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        //Remplazar el convertidor existente por el personalizado
        for (int i =0; i < messageConverts.size(); i++) {
            if (messageConverts.get(i) instanceof StringHttpMessageConverter){
                messageConverts.set(i,stringConverter);
                break;
            }
        }

        restTemplate.setMessageConverters(messageConverts);
        return restTemplate;
    }
}
