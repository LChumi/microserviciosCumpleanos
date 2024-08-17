package com.cumpleanos.reccomprobantes.utils;

import com.cumpleanos.reccomprobantes.exceptions.ConversionException;
import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlConverterUtils {

    public Factura convertirXmlAFactura(String xml){
        
        XmlMapper mapper = createSecureXmlMapper();
        try {
            Factura factura = mapper.readValue(xml, Factura.class);
            System.out.println("Transformando ......");
            return factura;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public ComprobanteRetencion convertirXmlAComprobanteRetencion(String xml){
        
        XmlMapper mapper = createSecureXmlMapper();
        try {
            ComprobanteRetencion retencion = mapper.readValue(xml, ComprobanteRetencion.class);
            System.out.println("Transformando retencion ....................");
            return retencion;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public NotaCredito convertirXmlANotaCredito(String xml){
        XmlMapper mapper = createSecureXmlMapper();
        try {
            NotaCredito notaCredito = mapper.readValue(xml, NotaCredito.class);
            System.out.println("Transformando notaCredito ......................");
            return notaCredito;
        }catch (JsonProcessingException e) {
            throw new ConversionException("Error al convertir el xml a Nota de Credito",e);
        }
    }
    

    private static XmlMapper createSecureXmlMapper(){
        XmlFactory xmlFactory = new XmlFactory();
        xmlFactory.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE); //Desactiva el autocierre de la fuente

        return new XmlMapper(xmlFactory);
    }

}
