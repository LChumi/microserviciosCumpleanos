package com.cumpleanos.assist.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MailTemplateLoader {

    public static String loadAndFillTemplate(String templateName, Map<String, String> variables) {
        try {
            // Cargar el archivo como String
            Resource resource = new ClassPathResource("templates/mails/" + templateName);
            byte[] bytes = resource.getInputStream().readAllBytes();
            String result = new String(bytes, StandardCharsets.UTF_8);

            // Reemplazar las variables {{variable}}
            if (variables != null) {
                for (Map.Entry<String, String> entry : variables.entrySet()) {
                    result = result.replace("{{" + entry.getKey() + "}}", entry.getValue());
                }
            }

            return result;
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar el template de correo: " + templateName, e);
        }
    }
}
