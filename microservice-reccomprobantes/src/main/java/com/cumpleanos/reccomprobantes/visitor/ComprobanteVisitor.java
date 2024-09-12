package com.cumpleanos.reccomprobantes.visitor;

import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;

public interface ComprobanteVisitor {
    void visit(Factura factura);
    void visit(NotaCredito notaCredito);
    void visit(ComprobanteRetencion comprobanteRetencion);
}
