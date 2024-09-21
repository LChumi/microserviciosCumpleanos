package com.cumpleanos.reccomprobantes.visitor;

import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;
import com.cumpleanos.reccomprobantes.service.ModelsServiceImpl;
import core.cumpleanos.models.entities.Sistema;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComprobantesProcessor implements ComprobanteVisitor {

    private final ModelsServiceImpl modelsService;

    /**
     *
     * Clase concreta que realiza operaciones especificas
     */
    @Override
    public void visit(Factura factura) {
        String ruc = factura.getInfoFactura().getIdentificacionComprador();
        Sistema empresa = modelsService.getEmpresaByRuc(ruc);
        if (empresa != null) {
            System.out.println(empresa.getNombre());
        } else {
            System.out.println("No se encontro la empresa con ruc: "+ruc);
        }
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
