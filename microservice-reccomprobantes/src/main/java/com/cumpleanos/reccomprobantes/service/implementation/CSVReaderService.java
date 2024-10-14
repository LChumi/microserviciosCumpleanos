package com.cumpleanos.reccomprobantes.service.implementation;

import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.core.models.entities.SriDocEleEmi;
import com.cumpleanos.reccomprobantes.persistence.models.csv.ComprobanteCsv;
import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.persistence.models.json.ComprobanteJson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.cumpleanos.reccomprobantes.util.ComprobantesUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CSVReaderService {

    private final ModelsServiceImpl modelsService;
    private final XMLConversionService xmlService;

    public List<Comprobante> parseCsvString(String csvContent) throws IOException {
        List<Comprobante> comprobantes = new ArrayList<>();
        List<ComprobanteCsv> comprobantesCsv = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(csvContent.getBytes(StandardCharsets.UTF_8)),
                StandardCharsets.UTF_8))) {
            String line;
            // Leer la cabecera del CSV (opcional si no necesitas los nombres de columna)
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t"); // Tab es el delimitador en tu archivo

                ComprobanteCsv comprobante = getComprobanteCsv(values);

                comprobantes.add(comprobante);
                comprobantesCsv.add(comprobante);
            }
        }
        try {
            log.info("Inicializacion proceso en for:{}", comprobantes.size());
            for (ComprobanteCsv comprobanteCsv:comprobantesCsv){
                procesoDoc(comprobanteCsv);
            }
        }catch (Exception e){
            log.error("Error la busqueda for:{}",e.getMessage());
        }
        return comprobantes;
    }

    private void procesoDoc(ComprobanteCsv csv) throws Exception {
        log.info("INICIANDO PROCESO CSV..............................");
        SriDocEleEmi docuemnto = modelsService.getSriDocByClaveAcceso(csv.getClaveAcceso());
        if (docuemnto == null) {
            Sistema empresa = modelsService.getEmpresaByRuc(csv.getIdentificacionReceptor());
            if (empresa != null) {
                SriDocEleEmi docSri = creaDoc(csv, empresa);
                if (docSri.getComprobante().equalsIgnoreCase("Comprobante de Retencion")) {
                    //SriDocEleEmi nuevo = modelsService.save(docSri);
                    log.info("Comprobante de Retencion agregado: {}", docSri);
                } else {
                    Cliente proveedor = modelsService.getByRucAndEmpresa(csv.getRucEmisor(), empresa.getId());
                    if (proveedor != null) {
                        System.out.println("---------------------------------------------------------------------------------------");
                        log.info("Proveedor existe {}", proveedor.getNombre());
                        //SriDocEleEmi nuevo = modelsService.save(docSri);
                    } else {
                        log.warn("Proveedor no existe ingresando a sri ");
                        ComprobanteJson json = modelsService.getComprobantesSri(csv.getClaveAcceso());
                        if (json != null) {
                            if (json.getRespuestaAutorizacionComprobante().getAutorizaciones() != null) {
                                String cleanedXml = cleanXml(json.getRespuestaAutorizacionComprobante().getAutorizaciones().getAutorizacion().getComprobante());
                                String transformedXml = transformXml(cleanedXml);
                                json.getRespuestaAutorizacionComprobante().getAutorizaciones().getAutorizacion().setComprobante(transformedXml);
                                xmlService.convertirXmlAComprobante(transformedXml,json.getRespuestaAutorizacionComprobante().getAutorizaciones().getAutorizacion().getFechaAutorizacion());
                            } else {
                                log.error("Ocurrio un problema la clave: {} no tiene un documento no se puede procesar ", csv.getClaveAcceso());
                            }
                        }
                    }
                }
            }
        }else {
            log.info("Docuemnto ya existe CA{}", docuemnto.getClaveAcceso());
        }
    }


}
