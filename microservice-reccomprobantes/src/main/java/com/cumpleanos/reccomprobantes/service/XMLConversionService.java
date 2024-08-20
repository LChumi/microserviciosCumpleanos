package com.cumpleanos.reccomprobantes.service;

import com.cumpleanos.reccomprobantes.models.xml.Comprobante;
import com.cumpleanos.reccomprobantes.models.xml.autorizacion.Autorizacion;
import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;
import com.cumpleanos.reccomprobantes.utils.XmlConverterUtils;
import org.springframework.stereotype.Service;

@Service
public class XMLConversionService {

    XmlConverterUtils xmlConverterUtils = new XmlConverterUtils();

    public Factura convertirXMlAFactura(String xml){
        return xmlConverterUtils.convertirXmlAFactura(xml);
    }

    public NotaCredito convertirXMlANotaCredito(String xml){
        return xmlConverterUtils.convertirXmlANotaCredito(xml);
    }

    public ComprobanteRetencion convertirXMlARetencion(String xml){
        return xmlConverterUtils.convertirXmlAComprobanteRetencion(xml);
    }

    public Comprobante convertirXMLAAutorizacion(String xml){
        return xmlConverterUtils.convertirXmlAAutorizacion(xml);
    }
}
