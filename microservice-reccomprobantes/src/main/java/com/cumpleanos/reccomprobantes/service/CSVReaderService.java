package com.cumpleanos.reccomprobantes.service;

import com.cumpleanos.reccomprobantes.models.csv.ComprobanteCsv;
import com.cumpleanos.reccomprobantes.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.models.json.ComprobanteJson;
import com.cumpleanos.reccomprobantes.utils.DateTimeUtils;
import core.cumpleanos.models.entities.Cliente;
import core.cumpleanos.models.entities.Sistema;
import core.cumpleanos.models.entities.SriDocEleEmi;
import core.cumpleanos.models.ids.SriDocEleEmiId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static com.cumpleanos.reccomprobantes.utils.ComprobantesUtils.cleanXml;
import static com.cumpleanos.reccomprobantes.utils.ComprobantesUtils.transformXml;

@Service
@RequiredArgsConstructor
public class CSVReaderService {

    private final ModelsServiceImpl modelsService;
    private final XMLConversionService xmlService;

    public List<Comprobante> parseCsvString(String csvContent) throws IOException {
        List<Comprobante> comprobantes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new StringReader(csvContent))) {
            String line;
            // Leer la cabecera del CSV (opcional si no necesitas los nombres de columna)
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t"); // Tab es el delimitador en tu archivo

                ComprobanteCsv comprobante = new ComprobanteCsv();
                comprobante.setRucEmisor(values[0]);
                comprobante.setRazonSocialEmisor(values[1]);
                comprobante.setTipoComprobante(values[2]);
                comprobante.setSerieComprobante(values[3]);
                comprobante.setClaveAcceso(values[4]);
                comprobante.setFechaAutorizacion(values[5]);
                comprobante.setFechaEmision(values[6]);
                comprobante.setIdentificacionReceptor(values[7]);
                comprobante.setValorSinImpuestos(values[8]);
                comprobante.setIva(values[9]);
                comprobante.setImporteTotal(values[10]);
                // Solo establecer el valor para `numeroDocumentoModificado` si está presente
                if (values.length > 11) {
                    comprobante.setNumeroDocumentoModificado(values[11]);
                } else {
                    comprobante.setNumeroDocumentoModificado(""); // O cualquier valor predeterminado
                }

                comprobantes.add(comprobante);
            }
        }
        return comprobantes;
    }

    private void procesoDoc(ComprobanteCsv csv) throws Exception {
        SriDocEleEmi docuemnto = modelsService.getSriDocByClaveAcceso(csv.getClaveAcceso());
        if (docuemnto == null) {
            Sistema empresa = modelsService.getEmpresaByRuc(csv.getIdentificacionReceptor());
            if(empresa != null) {
                SriDocEleEmi docSri = creaDoc(csv, empresa);
                if (docSri.getComprobante().equalsIgnoreCase("Comprobante de Retencion")){
                    //SriDocEleEmi nuevo = modelsService.save(docSri);
                }else {
                    Cliente proveedor = modelsService.getByRucAndEmpresa(csv.getRucEmisor(), empresa.getId());
                    if (proveedor != null) {
                        //SriDocEleEmi nuevo = modelsService.save(docSri);
                    } else {
                        ComprobanteJson json = modelsService.getComprobantesSri(csv.getClaveAcceso());
                        if (json != null) {
                            String cleanedXml = cleanXml(json.getRespuestaAutorizacionComprobante().getAutorizaciones().getAutorizacion().getComprobante());
                            String transformedXml = transformXml(cleanedXml);
                            json.getRespuestaAutorizacionComprobante().getAutorizaciones().getAutorizacion().setComprobante(transformedXml);

                            xmlService.convertirXmlAComprobante(transformedXml);
                        }
                    }
                }
            }
        }
    }

    private SriDocEleEmi creaDoc(ComprobanteCsv comp, Sistema empresa) {
        SriDocEleEmi doc = new SriDocEleEmi();
        SriDocEleEmiId id = new SriDocEleEmiId();
        id.setEmpresa(empresa.getId());
        id.setNumeroAutorizacion(comp.getClaveAcceso());
        doc.setId(id);
        doc.setRucEmisor(comp.getRucEmisor());
        doc.setRazonSocialEmisor(comp.getRazonSocialEmisor());
        doc.setComprobante(comp.getTipoComprobante());
        doc.setSerieComprobante(comp.getSerieComprobante());
        doc.setClaveAcceso(comp.getClaveAcceso());
        doc.setFechaAutorizacion(DateTimeUtils.parseDateTime(comp.getFechaAutorizacion()));
        doc.setFechaEmision(DateTimeUtils.parseDate(comp.getFechaEmision()));
        doc.setIdentificacionReceptor(comp.getIdentificacionReceptor());
        return doc;
    }
}
