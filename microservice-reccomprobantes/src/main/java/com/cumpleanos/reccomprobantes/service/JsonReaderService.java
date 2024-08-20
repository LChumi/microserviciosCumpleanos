package com.cumpleanos.reccomprobantes.service;

import com.cumpleanos.reccomprobantes.exceptions.ConversionException;
import com.cumpleanos.reccomprobantes.models.json.ComprobanteJson;
import com.cumpleanos.reccomprobantes.models.xml.ComprobanteXml;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

@Service
@RequiredArgsConstructor
public class JsonReaderService {

    private final XMLConversionService xmlService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ComprobanteXml convertirStringJsonToComprobante(String json) {
        try {
            ComprobanteJson comprobante = objectMapper.readValue(json, ComprobanteJson.class);

            // Limpiar y transformar el XML en cada comprobante
            String cleanedXml = cleanXml(comprobante.getRespuestaAutorizacionComprobante().getAutorizaciones().getAutorizacion().getComprobante());

            String transformedXml = transformXml(cleanedXml);
            comprobante.getRespuestaAutorizacionComprobante().getAutorizaciones().getAutorizacion().setComprobante(transformedXml);

            return xmlService.convertirXmlAComprobante(transformedXml);
        } catch (IOException e) {
            throw new ConversionException("Error al convertir el JSON del comprobante", e);
        } catch (Exception e) {
            throw new ConversionException("Error al transformar el XML del comprobante", e);
        }
    }

    private static String cleanXml(String xml) {
        // Eliminar los caracteres de escape \" para obtener XML limpio
        return xml.replace("\\\"", "\"");
    }

    private static String transformXml(String xml) throws Exception {
        // Parsear el XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new StringReader(xml)));

        // Convertir el documento de vuelta a XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(document), new StreamResult(writer));

        return writer.toString();
    }
}
