package com.cumpleanos.reccomprobantes.util;

import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.entities.Sistema;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class FilesUtils {

    private final String ruta;

    public void guardarCliente(Cliente c, Sistema sis) throws IOException {
        String nombreArchivo = String.format("%s.txt", c.getRucCedula());
        Path rutaArchivo = Paths.get(ruta, nombreArchivo);

        // Crear un nuevo objeto que solo contiene los campos necesarios
        Map<String, String> clienteDatos = new HashMap<>();
        clienteDatos.put("RUC/Cedula", c.getRucCedula());
        clienteDatos.put("Nombre", c.getNombre());
        clienteDatos.put("Empresa", sis.getNombre());

        ObjectMapper mapper = new ObjectMapper();
        String jsonCliente = mapper.writeValueAsString(clienteDatos);

        // Agregar cliente al archivo
        Files.write(rutaArchivo, Collections.singletonList(jsonCliente), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public void eliminarArchivo(String rucCedula) throws IOException {
        String nombreArchivo = String.format("%s.txt", rucCedula);
        Path rutaArchivo = Paths.get(ruta, nombreArchivo);
        Files.deleteIfExists(rutaArchivo);
    }

    public List<Map<String, String>> leerClientes(String rucCedula) throws IOException {
        String nombreArchivo = String.format("%s.txt", rucCedula);
        Path rutaArchivo = Paths.get(ruta, nombreArchivo);

        // Leer todas las líneas del archivo
        List<String> lineas = Files.readAllLines(rutaArchivo);

        ObjectMapper mapper = new ObjectMapper();

        // Convertir cada línea de JSON a un Map
        return lineas.stream()
                .map(linea -> {
                    try {
                        return mapper.readValue(linea, new TypeReference<Map<String, String>>() {});
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }

    public void eliminarTodosLosArchivos() throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(ruta))) {
            stream.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".txt"))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            throw new RuntimeException("Error al eliminar el archivo: " + path, e);
                        }
                    });
        }
    }

    public List<Map<String, String>> leerClientes() throws IOException {
        List<Map<String, String>> listaClientes = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try (Stream<Path> stream = Files.list(Paths.get(ruta))) {
            stream.filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".txt"))
                    .forEach(path -> {
                        try {
                            List<String> contenidoArchivo = obtenerContenidoObservacion(path.getFileName().toString());
                            contenidoArchivo.forEach(linea -> {
                                try {
                                    Map<String,String> cliente = objectMapper.readValue(linea, new TypeReference<Map<String, String>>() {});
                                    listaClientes.add(cliente);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
        return listaClientes;
    }

    private List<String> obtenerContenidoObservacion(String nombreArchivo) throws IOException{
        Path rutaArchivo = Paths.get(ruta,nombreArchivo);
        return Files.readAllLines(rutaArchivo);
    }
}