package com.cumpleanos.reccomprobantes.service;

import com.cumpleanos.reccomprobantes.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.utils.XmlConverterUtils;
import com.cumpleanos.reccomprobantes.visitor.ComprobanteVisitor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class XMLConversionService {

    private final ComprobanteVisitor visitor;

    public Comprobante convertirXmlAComprobante(String xml,String fechaAutorizacion){
        XmlConverterUtils xmlConverterUtils = new XmlConverterUtils(visitor);
        return xmlConverterUtils.convertirXmlAAutorizacion(xml, fechaAutorizacion);
    }
}
