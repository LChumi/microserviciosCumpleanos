package com.cumpleanos.reccomprobantes.service;

import com.cumpleanos.reccomprobantes.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.models.xml.ComprobanteXml;
import com.cumpleanos.reccomprobantes.utils.XmlConverterUtils;
import org.springframework.stereotype.Service;

@Service
public class XMLConversionService {

    XmlConverterUtils xmlConverterUtils = new XmlConverterUtils();

    public Comprobante convertirXmlAComprobante(String xml){
        return xmlConverterUtils.convertirXmlAAutorizacion(xml);
    }
}
