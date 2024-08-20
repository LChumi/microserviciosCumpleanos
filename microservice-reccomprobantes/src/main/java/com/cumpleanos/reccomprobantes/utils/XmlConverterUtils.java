package com.cumpleanos.reccomprobantes.utils;

import com.cumpleanos.reccomprobantes.exceptions.ConversionException;
import com.cumpleanos.reccomprobantes.models.xml.Comprobante;
import com.cumpleanos.reccomprobantes.models.xml.autorizacion.Autorizacion;
import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

public class XmlConverterUtils {

    public Factura convertirXmlAFactura(String xml){
        try {
            JAXBContext context = JAXBContext.newInstance(Factura.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Factura) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new ConversionException("Error al convertir el xml a Factura",e);
        }
    }

    public ComprobanteRetencion convertirXmlAComprobanteRetencion(String xml){
        try {
            JAXBContext context = JAXBContext.newInstance(ComprobanteRetencion.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (ComprobanteRetencion) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new ConversionException("Error al convertir el xml a Factura",e);
        }
    }

    public NotaCredito convertirXmlANotaCredito(String xml){
        try {
            JAXBContext context = JAXBContext.newInstance(NotaCredito.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (NotaCredito) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new ConversionException("Error al convertir el xml a Factura",e);
        }
    }

    public Comprobante convertirXmlAAutorizacion(String xml) {
        try {
            // Crear el contexto JAXB para la clase Autorizacion
            JAXBContext context = JAXBContext.newInstance(Autorizacion.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Deserializar el XML principal
            Autorizacion autorizacion = (Autorizacion) unmarshaller.unmarshal(new StringReader(xml));

            // Obtener el contenido de comprobante
            String comprobanteXml = autorizacion.getComprobante();
            if (comprobanteXml != null && !comprobanteXml.trim().isEmpty()) {
                // Crear un contexto JAXB para las subclases de Comprobante
                JAXBContext comprobanteContext = JAXBContext.newInstance(Factura.class, NotaCredito.class, ComprobanteRetencion.class);
                Unmarshaller comprobanteUnmarshaller = comprobanteContext.createUnmarshaller();

                // Usar StringReader para deserializar el XML de comprobante
                StringReader reader = new StringReader(comprobanteXml);

                // Deserializar el contenido de comprobante en una de las subclases de Comprobante
                Comprobante comprobante = (Comprobante) comprobanteUnmarshaller.unmarshal(reader);

                return comprobante;
            }
        } catch (JAXBException e) {
            throw new ConversionException("Error al convertir el XML a Autorizacion", e);
        }
        return null;
    }

}
