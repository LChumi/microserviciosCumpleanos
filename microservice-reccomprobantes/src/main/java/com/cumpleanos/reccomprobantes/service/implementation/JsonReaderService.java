package com.cumpleanos.reccomprobantes.service.implementation;

import com.cumpleanos.reccomprobantes.service.exception.ConversionException;
import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.persistence.models.json.ComprobanteJson;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;

import static com.cumpleanos.reccomprobantes.util.ComprobantesUtils.cleanXml;
import static com.cumpleanos.reccomprobantes.util.ComprobantesUtils.transformXml;

@Service
@RequiredArgsConstructor
public class JsonReaderService {

    private final XMLConversionService xmlService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Comprobante convertirStringJsonToComprobante(String json) {
        try {
            ComprobanteJson comprobante = objectMapper.readValue(json, ComprobanteJson.class);

            // Limpiar y transformar el XML en cada comprobante
            String cleanedXml = cleanXml(comprobante.getRespuestaAutorizacionComprobante().getAutorizaciones().getAutorizacion().getComprobante());

            String transformedXml = transformXml(cleanedXml);
            comprobante.getRespuestaAutorizacionComprobante().getAutorizaciones().getAutorizacion().setComprobante(transformedXml);

            return xmlService.convertirXmlAComprobante(transformedXml, comprobante.getRespuestaAutorizacionComprobante().getAutorizaciones().getAutorizacion().getFechaAutorizacion());
        } catch (IOException e) {
            throw new ConversionException("Error al convertir el JSON del comprobante", e);
        } catch (Exception e) {
            throw new ConversionException("Error al transformar el XML del comprobante", e);
        }
    }

}
