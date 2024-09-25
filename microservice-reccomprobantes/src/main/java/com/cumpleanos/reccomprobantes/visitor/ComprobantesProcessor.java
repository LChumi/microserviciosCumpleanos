package com.cumpleanos.reccomprobantes.visitor;

import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;
import com.cumpleanos.reccomprobantes.service.ModelsServiceImpl;
import com.cumpleanos.reccomprobantes.utils.DateTimeUtils;
import core.cumpleanos.models.entities.Sistema;
import core.cumpleanos.models.entities.SriDocEleEmi;
import core.cumpleanos.models.ids.SriDocEleEmiId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComprobantesProcessor implements ComprobanteVisitor {

    private final ModelsServiceImpl modelsService;

    @Override
    public void visit(Factura factura) {
        procesarDoc(factura.getInfoTributaria().getClaveAcceso(),
                factura.getInfoFactura().getIdentificacionComprador(),
                factura.getTipoComprobante(),
                factura.getInfoTributaria().noComprobante(),
                factura.getInfoTributaria().getRuc(),
                DateTimeUtils.parseDate(factura.getInfoFactura().getFechaEmision()),
                DateTimeUtils.parseDateTime(factura.getFechaAutorizacion()),
                factura.getInfoFactura().getRazonSocialComprador());
    }

    @Override
    public void visit(NotaCredito notaCredito) {
        procesarDoc(notaCredito.getInfoTributaria().getClaveAcceso(),
                notaCredito.getInfoNotaCredito().getIdentificacionComprador(),
                notaCredito.getTipoComprobante(),
                notaCredito.getInfoTributaria().noComprobante(),
                notaCredito.getInfoTributaria().getRuc(),
                DateTimeUtils.parseDate(notaCredito.getInfoNotaCredito().getFechaEmision()),
                DateTimeUtils.parseDateTime(notaCredito.getFechaAutorizacion()),
                notaCredito.getInfoNotaCredito().getRazonSocialComprador());
    }

    @Override
    public void visit(ComprobanteRetencion comprobanteRetencion) {
        procesarDoc(comprobanteRetencion.getInfoTributaria().getClaveAcceso(),
                comprobanteRetencion.getInfoCompRetencion().getIdentificacionSujetoRetenido(),
                comprobanteRetencion.getTipoComprobante(),
                comprobanteRetencion.getInfoTributaria().noComprobante(),
                comprobanteRetencion.getInfoTributaria().getRuc(),
                DateTimeUtils.parseDate(comprobanteRetencion.getInfoCompRetencion().getFechaEmision()),
                DateTimeUtils.parseDateTime(comprobanteRetencion.getFechaAutorizacion()),
                comprobanteRetencion.getInfoCompRetencion().getIdentificacionSujetoRetenido());
    }

    private void procesarDoc(String claveAcceso, String ruc, String tipoComprobante, String serieComprobante,
                             String rucEmisor, LocalDate fechaEmision, ZonedDateTime fechaAutorizacion,
                             String identificacionReceptor) {
        SriDocEleEmi documento = modelsService.getSriDocByClaveAcceso(claveAcceso);

        if (documento != null) {
            log.info("Comprobante ya se encuentra registrado en la base de datos");
        } else {
            log.warn("No se encontró el comprobante con la clave de acceso: {}", claveAcceso);
            Sistema empresa = modelsService.getEmpresaByRuc(ruc);

            if (empresa != null) {
                SriDocEleEmi docSri = crearSriDoc(empresa, claveAcceso, tipoComprobante, serieComprobante,
                        rucEmisor,ruc, fechaEmision, fechaAutorizacion,
                        identificacionReceptor);
                log.info("Comprobante creado en BD documento -> {}", docSri);
            } else {
                log.error("La empresa no existe o el RUC está incorrecto");
            }
        }
    }

    private SriDocEleEmi crearSriDoc(
            Sistema empresa,
            String claveAcceso,
            String tipoComprobante,
            String serieComprobante,
            String rucEmisor,
            String razonSocialEmisor,
            LocalDate fechaEmision,
            ZonedDateTime fechaAutorizacion,
            String identificacionReceptor)
    {
        SriDocEleEmi docSri = new SriDocEleEmi();
        SriDocEleEmiId idSri = new SriDocEleEmiId();
        idSri.setEmpresa(empresa.getId());
        idSri.setNumeroAutorizacion(claveAcceso);
        docSri.setId(idSri);
        docSri.setComprobante(tipoComprobante);
        docSri.setSerieComprobante(serieComprobante);
        docSri.setRucEmisor(rucEmisor);
        docSri.setRazonSocialEmisor(razonSocialEmisor);
        docSri.setFechaEmision(fechaEmision);
        docSri.setFechaAutorizacion(fechaAutorizacion);
        docSri.setIdentificacionReceptor(identificacionReceptor);
        docSri.setClaveAcceso(claveAcceso);
        return docSri;
    }
}