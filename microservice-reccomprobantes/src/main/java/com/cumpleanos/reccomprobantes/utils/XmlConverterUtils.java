package com.cumpleanos.reccomprobantes.utils;

import com.cumpleanos.reccomprobantes.exceptions.ConversionException;
import com.cumpleanos.reccomprobantes.models.xml.ComprobanteXml;
import com.cumpleanos.reccomprobantes.models.xml.autorizacion.Autorizacion;
import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;
import com.cumpleanos.reccomprobantes.visitor.ComprobanteVisitor;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

@RequiredArgsConstructor
public class XmlConverterUtils {

    private final ComprobanteVisitor visitor;

    public ComprobanteXml convertirXmlAAutorizacion(String xml,String fechaAutorizacion) {
        try {
            Unmarshaller unmarshaller = createUnmarsaller(Autorizacion.class);
            if (esXmlDeAutorizacion(xml)) {
                System.out.println("Es Autorizacion ");
                Autorizacion autorizacion = (Autorizacion) unmarshaller.unmarshal(new StringReader(xml));
                String comprobanteXml = autorizacion.getComprobante();
                if (comprobanteXml != null && !comprobanteXml.trim().isEmpty()) {
                    Unmarshaller comprobanteUnmarshaller = createUnmarsaller(Factura.class, NotaCredito.class, ComprobanteRetencion.class);
                    StringReader stringReader = new StringReader(comprobanteXml);
                    ComprobanteXml comprobante = (ComprobanteXml) comprobanteUnmarshaller.unmarshal(stringReader);
                    comprobante.setTipoComprobante(identificarTipoComprobante(comprobanteXml));
                    comprobante.setFechaAutorizacion(autorizacion.getFechaAutorizacion());
                    comprobante.setNumeroAutorizacion(autorizacion.getNumeroAutorizacion());
                    comprobante.accept(visitor);
                    return comprobante;
                }
            } else {
                Unmarshaller comprobanteUnmarshaller = createUnmarsaller(Factura.class, NotaCredito.class, ComprobanteRetencion.class);
                StringReader reader = new StringReader(xml);
                ComprobanteXml comprobante = (ComprobanteXml) comprobanteUnmarshaller.unmarshal(reader);
                comprobante.setTipoComprobante(identificarTipoComprobante(xml));
                comprobante.setFechaAutorizacion(fechaAutorizacion);
                comprobante.setNumeroAutorizacion(comprobante.getInfoTributaria().getClaveAcceso());
                comprobante.accept(visitor);
                return comprobante;
            }
        } catch (JAXBException e) {
            throw new ConversionException("Error al convertir el XML a Autorizacion", e);
        }
        return null;
    }

    private boolean esXmlDeAutorizacion(String xml) {
        return xml.contains("<autorizacion>");
    }

    protected String identificarTipoComprobante(String comprobanteXml) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(comprobanteXml));
            Document document = builder.parse(source);

            String rootElement = document.getDocumentElement().getNodeName();
            return switch (rootElement) {
                case "factura" -> "Factura";
                case "notaCredito" -> "Nota de Credito";
                case "comprobanteRetencion" -> "Comprobante de Retencion";
                default -> throw new IllegalArgumentException("Tipo de comprobante no reconocido: " + rootElement);
            };
        }catch (Exception e){
            throw new ConversionException("Error al obtener el tipo de comprobante", e);
        }
    }

    private Unmarshaller createUnmarsaller(Class<?>... classesToBeBound) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classesToBeBound);
        return context.createUnmarshaller();
    }
}
