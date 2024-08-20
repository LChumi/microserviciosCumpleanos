package com.cumlpeanos.pos.utils;

import com.cumlpeanos.pos.models.api.DatosRecepcionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class FilesUtils {

    @Value("${file.path}")
    private String ruta;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Crea archivos cuando algo se rompe en el sistema.
     */
    public void crearArchivoDatosNoGuardado(Long usrLiquida, Long empresa, DatosRecepcionResponse response) {
        if (ruta == null || ruta.isEmpty()) {
            throw new IllegalArgumentException("La ruta no puede ser null o vacía");
        }

        try {
            String nombreArchivo = String.format("response_%s_%s.json", usrLiquida, empresa);
            Path rutaArchivo = Paths.get(ruta, nombreArchivo);

            Files.createDirectories(rutaArchivo.getParent());

            String json = objectMapper.writeValueAsString(response);
            Files.writeString(rutaArchivo, json);
        } catch (IOException e) {
            log.error("ERROR al crear el archivo no guardado: {}", e.getMessage());
            throw new RuntimeException("No se pudo crear el archivo de respaldo, mensaje: " + e.getMessage(), e);
        }
    }

    public List<DatosRecepcionResponse> listar() throws IOException {
        List<DatosRecepcionResponse> responses = new ArrayList<>();

        try (Stream<Path> stream = Files.list(Paths.get(ruta))) {
            responses = stream.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().startsWith("response_"))
                    .flatMap(path -> {
                        try {
                            return Files.readAllLines(path)
                                    .stream()
                                    .map(linea -> {
                                        try {
                                            return objectMapper.readValue(linea, DatosRecepcionResponse.class);
                                        } catch (IOException e) {
                                            log.error("Error al leer el valor JSON: {}", e.getMessage());
                                            return null;
                                        }
                                    })
                                    .filter(Objects::nonNull);
                        } catch (IOException e) {
                            log.error("Error al leer el archivo: {}", e.getMessage());
                            return Stream.empty();
                        }
                    })
                    .sorted()
                    .collect(Collectors.toList());
        }

        return responses;
    }

    /**
     * Borra archivos cuando se necesite limpieza del sistema.
     */
    public void borrarArchivo(String nombreArchivo) {
        try {
            Path rutaArchivo = Paths.get(ruta, nombreArchivo);
            Files.deleteIfExists(rutaArchivo);
        } catch (IOException e) {
            log.error("ERROR al borrar el archivo: {}", e.getMessage());
            throw new RuntimeException("No se pudo borrar el archivo, mensaje: " + e.getMessage(), e);
        }
    }
}