package com.cumpleanos.reccomprobantes.utils;

import com.cumpleanos.reccomprobantes.exceptions.ConversionException;
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

}
