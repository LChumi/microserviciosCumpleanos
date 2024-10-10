package com.cumpleanos.reccomprobantes.service.implementation;

import com.cumpleanos.reccomprobantes.persistence.models.entity.Comprobante;
import com.cumpleanos.reccomprobantes.util.XmlConverterUtils;
import com.cumpleanos.reccomprobantes.patterns.visitor.ComprobanteVisitor;
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
