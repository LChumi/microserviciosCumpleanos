package com.cumpleanos.reccomprobantes.service.implementation;

import com.cumpleanos.core.models.dto.EmailRecord;
import com.cumpleanos.reccomprobantes.configuration.RutasConfig;
import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.util.FilesUtils;
import com.cumpleanos.reccomprobantes.util.MessagesUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CoordinatorService {

    private final CSVReaderService csvReaderService;
    private final XMLConversionService xmlConversionService;
    private final JsonReaderService jsonReaderService;
    private final ModelsServiceImpl modelsService;
    private final RutasConfig rutas;

    public Object processFile(MultipartFile file,String email) throws IOException {
        String filename  = file.getOriginalFilename();
        String fileContent = new String(file.getBytes(), StandardCharsets.UTF_8);

        assert filename != null;
        if (filename.endsWith(".txt")){
            return processCsv(fileContent,email);
        } else if (filename.endsWith(".xml")){
            return processXml(fileContent, email);
        } else if (filename.endsWith(".json")){
            return processJson(fileContent,email);
        } else {
            throw new IllegalArgumentException("Tipo de archivo no soportado"+ filename);
        }
    }

    public Object processString(String inputString, String email) throws IOException {
        if (inputString.trim().startsWith("{")){
            return processJson(inputString, email);
        } else if (inputString.trim().startsWith("<")){
            return processXml(inputString, email);
        } else {
            return processCsv(inputString, email);
        }
    }

    private List<Comprobante> processCsv(String csvContent, String email){
        try {
            List<Comprobante> lista= csvReaderService.parseCsvString(csvContent, email);
            verificarArchivos(email);
            return lista;
        } catch (IOException e){
            throw new RuntimeException("Error al procesar CSV ",e);
        }
    }

    private Comprobante processXml(String xmlContent, String email) throws IOException {
        Comprobante comp = xmlConversionService.convertirXmlAComprobante(xmlContent, "");
        verificarArchivos(email);
        return comp;
    }

    private Comprobante processJson(String jsonContent, String email) throws IOException {
        Comprobante comp = jsonReaderService.convertirStringJsonToComprobante(jsonContent);
        verificarArchivos(email);
        return comp;
    }

    private void verificarArchivos(String correo) throws IOException {
        FilesUtils files = new FilesUtils(rutas.getRutaCliente());
        List<Map<String, String>> clientesCamposNull = files.leerClientes();
        if (clientesCamposNull != null && !clientesCamposNull.isEmpty()) {
            String empresa =  clientesCamposNull.get(0).get("Empresa");
            String mensaje= MessagesUtils.mensajeHtmlCamposNulosClientes(clientesCamposNull,empresa);
            String asunto = "Campos no registrados de Proveedores";

            EmailRecord email = new EmailRecord(
                    new String[]{correo},
                    asunto,
                    mensaje
            );
            modelsService.enviarEmail(email);
            files.eliminarTodosLosArchivos();
        }
    }
}
