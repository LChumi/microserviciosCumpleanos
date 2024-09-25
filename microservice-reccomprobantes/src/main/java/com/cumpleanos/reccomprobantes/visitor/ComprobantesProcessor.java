package com.cumpleanos.reccomprobantes.visitor;

import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;
import com.cumpleanos.reccomprobantes.service.ModelsServiceImpl;
import core.cumpleanos.models.entities.Sistema;
import core.cumpleanos.models.entities.SriDocEleEmi;
import core.cumpleanos.models.ids.SriDocEleEmiId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComprobantesProcessor implements ComprobanteVisitor {

    private final ModelsServiceImpl modelsService;

    /**
     * Clase concreta que realiza operaciones especificas
     */
    @Override
    public void visit(Factura factura) {
        String claveAcceso = factura.getInfoTributaria().getClaveAcceso();
        String ruc = factura.getInfoFactura().getIdentificacionComprador();
        SriDocEleEmi documento= modelsService.getSriDocByClaveAcceso(claveAcceso);
        if (documento != null) {
            log.info("Comprobante ya se encuentra registrado en la base de datos");
        } else {
            Sistema empresa = modelsService.getEmpresaByRuc(ruc);
            if (empresa != null) {
                SriDocEleEmi docSri = new SriDocEleEmi();
                SriDocEleEmiId idSri = new SriDocEleEmiId();
                docSri.setId(idSri);
                idSri.setEmpresa(empresa.getId());
                idSri.setNumeroAutorizacion(claveAcceso);
                docSri.setComprobante(factura.getTipoComprobante());
                docSri.setSerieComprobante(factura.getInfoTributaria().getCodDoc()+"-"+factura.getInfoTributaria().getEstab()+"-"+factura.getInfoTributaria().getPtoEmi());
                docSri.setRucEmisor(factura.getInfoTributaria().getRuc());
                docSri.setRazonSocialEmisor(factura.getInfoTributaria().getRazonSocial());
                docSri.setFechaEmision(LocalDate.parse(factura.getInfoFactura().getFechaEmision()));
                docSri.setFechaAutorizacion(LocalDate.parse(factura.getFechaAutorizacion()));
                docSri.setIdentificacionReceptor(factura.getInfoFactura().getRazonSocialComprador());
                docSri.setClaveAcceso(claveAcceso);
            } else {
                log.error("La empresa no existe o el ruc esta incorrecto");
            }
        }
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
