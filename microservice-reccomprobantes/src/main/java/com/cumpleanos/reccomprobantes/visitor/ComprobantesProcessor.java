package com.cumpleanos.reccomprobantes.visitor;

import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;

public class ComprobantesProcessor implements ComprobanteVisitor {
    /**
     *
     * Clase concreta que realiza operaciones especificas
     */
    @Override
    public void visit(Factura factura) {
        System.out.println("datos Facturacion");
    }

    @Override
    public void visit(NotaCredito notaCredito) {
        System.out.println("datos Nota Credito");
    }

    @Override
    public void visit(ComprobanteRetencion comprobanteRetencion) {
        System.out.println("datos comprobante Retencion");
    }
}
