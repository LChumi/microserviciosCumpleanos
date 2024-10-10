package com.cumpleanos.reccomprobantes.service.implementation;

import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoordinatorService {

    private final CSVReaderService csvReaderService;
    private final XMLConversionService xmlConversionService;
    private final JsonReaderService jsonReaderService;

    public Object processFile(MultipartFile file) throws IOException {
        String filename  = file.getOriginalFilename();
        String fileContent = new String(file.getBytes(), StandardCharsets.UTF_8);

        assert filename != null;
        if (filename.endsWith(".txt")){
            return processCsv(fileContent);
        } else if (filename.endsWith(".xml")){
            return processXml(fileContent);
        } else if (filename.endsWith(".json")){
            return processJson(fileContent);
        } else {
            throw new IllegalArgumentException("Tipo de archivo no soportado"+ filename);
        }
    }

    public Object processString(String inputString){
        if (inputString.trim().startsWith("{")){
            return processJson(inputString);
        } else if (inputString.trim().startsWith("<")){
            return processXml(inputString);
        } else {
            return processCsv(inputString);
        }
    }

    private List<Comprobante> processCsv(String csvContent){
        try {
            return csvReaderService.parseCsvString(csvContent);
        } catch (IOException e){
            throw new RuntimeException("Error al procesar CSV ",e);
        }
    }

    private Comprobante processXml(String xmlContent){
        return xmlConversionService.convertirXmlAComprobante(xmlContent, "");
    }

    private Comprobante processJson(String jsonContent){
        return jsonReaderService.convertirStringJsonToComprobante(jsonContent);
    }

}
