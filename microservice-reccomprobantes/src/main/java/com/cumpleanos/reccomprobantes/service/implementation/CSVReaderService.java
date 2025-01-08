package com.cumpleanos.reccomprobantes.service.implementation;

import com.cumpleanos.core.models.dto.ClienteRecord;
import com.cumpleanos.core.models.entities.*;
import com.cumpleanos.core.models.enums.ParametroEnum;
import com.cumpleanos.reccomprobantes.persistence.models.csv.ComprobanteCsv;
import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.persistence.models.json.ComprobanteJson;
import com.cumpleanos.reccomprobantes.util.ComprobantesUtils;
import com.cumpleanos.reccomprobantes.util.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.cumpleanos.reccomprobantes.util.ComprobantesUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CSVReaderService {

    private final ModelsServiceImpl modelsService;
    private final XMLConversionService xmlService;

    public List<Comprobante> parseCsvString(String csvContent, String email) throws IOException {
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
                    SriDocEleEmi nuevo = modelsService.save(docSri);
                    log.info("Comprobante de Retencion agregado: {}", nuevo);
                } else {
                    ClienteRecord proveedor = modelsService.getByRucAndEmpresa(csv.getRucEmisor(),(short)2, empresa.getId());
                    if (proveedor != null) {
                        System.out.println("---------------------------------------------------------------------------------------");
                        log.info("Proveedor existe {}", proveedor.nombre());
                        saveAndVerifyAutClient(docSri,csv,proveedor.codigo(),empresa);
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
            } else {
                log.info("Empresa no existe");
            }
        }else {
            log.info("Docuemnto ya existe clave acceso: {}", docuemnto.getId());
        }
    }


    private void saveAndVerifyAutClient(SriDocEleEmi docSri,ComprobanteCsv csv, Long cliCodigo, Sistema empresa) {
        SriDocEleEmi nuevo = modelsService.save(docSri);
        verificarAutclient(csv, cliCodigo, empresa);
        log.info("Comprobante creado en BD documento -> {}", nuevo);
    }

    private void verificarAutclient(ComprobanteCsv csv, Long cliente, Sistema sis){
        if (csv.getClaveAcceso()==null){
            log.warn("El archivo no tiene numero de autorizacion {} en la empresa {}", csv.getSerieComprobante(), sis.getNombre());
        }
        String numAut = csv.getClaveAcceso();
        Long empresa = sis.getId();

        Autcliente encontrado = modelsService.getAutCliente(numAut, empresa);
        if (encontrado == null) {
            Autcliente autcliente = ComprobantesUtils.crearAutClienteCsv(csv, cliente, sis);
            autcliente.setAclTablacoa(obtenerParametro(empresa));
            RetDato retDato =modelsService.getRetDato(empresa,autcliente.getAclTablacoa(),ComprobantesUtils.identificarTipoDoc(csv.getTipoComprobante()));
            autcliente.setRetDato(retDato);
            autcliente.getId().setRetdato(retDato.getId().getCodigo());
            autcliente.setValFecha(DateTimeUtils.parseDate(csv.getFechaEmision()));
            Autcliente nuevo =modelsService.saveAutCliente(autcliente);
            log.info("autcliente nuevo creado: {}", nuevo);
        }
    }

    private Long obtenerParametro(Long empresa) {
        return modelsService.verificarParametro(
                empresa,
                ParametroEnum.COM_COA_TIPOCOM.getSigla(),
                ParametroEnum.COM_COA_TIPOCOM.getSecuencia(),
                2
        );
    }

}
