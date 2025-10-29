package com.cumpleanos.reccomprobantes.patterns.visitor;

import com.cumpleanos.reccomprobantes.persistence.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.persistence.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.persistence.models.xml.notaDebito.NotaDebito;
import com.cumpleanos.reccomprobantes.persistence.models.xml.retencion.ComprobanteRetencion;

public interface ComprobanteVisitor {
    void visit(Factura factura);
    void visit(NotaCredito notaCredito);
    void visit(ComprobanteRetencion comprobanteRetencion);
    void visit(NotaDebito notaDebito);
}
