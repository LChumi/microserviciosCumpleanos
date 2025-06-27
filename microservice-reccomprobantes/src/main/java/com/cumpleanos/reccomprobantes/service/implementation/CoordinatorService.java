package com.cumpleanos.reccomprobantes.service.implementation;

import com.cumpleanos.common.records.CompanyParametersRecord;
import com.cumpleanos.common.records.EmailRecord;
import com.cumpleanos.reccomprobantes.configuration.RutasConfig;
import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.util.FilesUtils;
import com.cumpleanos.reccomprobantes.util.MessagesUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CoordinatorService {

    private final CSVReaderService csvReaderService;
    private final XMLConversionService xmlConversionService;
    private final JsonReaderService jsonReaderService;
    private final ModelsServiceImpl modelsService;
    private final RutasConfig rutas;
    private final MongoClientServiceImpl mongoClientService;

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
            List<Comprobante> lista= csvReaderService.parseCsvString(csvContent);
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
            String empresaId = clientesCamposNull.get(0).get("EmpresaId");
            String logoB64 = getLogoBase64(empresaId);
            String mensaje= MessagesUtils.mensajeHtmlCamposNulosClientes(clientesCamposNull,empresa, logoB64);
            String asunto = "Informe de nuevos proveedores registrados";

            EmailRecord email = new EmailRecord(
                    new String[]{correo},
                    asunto,
                    mensaje
            );
            modelsService.enviarEmail(email);
            files.eliminarTodosLosArchivos();
        }
    }

    private String getLogoBase64(String empresa) {
        Long companyId = Long.valueOf(empresa);
        CompanyParametersRecord company = mongoClientService.getCompanyParameters(companyId);

        if (company == null) {
            log.warn("No se encontró información acerca de la empresa: {}", companyId);
            return null;
        }

        String primaryLogo = company.primaryLogo();
        if (primaryLogo == null) {
            log.warn("No se encontró información de logo de la empresa id: {}", companyId);
            return null;
        }

        return primaryLogo;
    }
}
