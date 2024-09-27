package com.cumpleanos.reccomprobantes.visitor;

import com.cumpleanos.reccomprobantes.models.xml.InfoTributaria;
import com.cumpleanos.reccomprobantes.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.models.xml.retencion.ComprobanteRetencion;
import com.cumpleanos.reccomprobantes.service.ModelsServiceImpl;
import com.cumpleanos.reccomprobantes.utils.DateTimeUtils;
import core.cumpleanos.models.entities.Cliente;
import core.cumpleanos.models.entities.Sistema;
import core.cumpleanos.models.entities.SriDocEleEmi;
import core.cumpleanos.models.ids.ClienteId;
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
        procesarDoc(
                factura.getInfoTributaria(),
                factura.getInfoFactura().getIdentificacionComprador(),
                factura.getTipoComprobante(),
                DateTimeUtils.parseDate(factura.getInfoFactura().getFechaEmision()),
                DateTimeUtils.parseDateTime(factura.getFechaAutorizacion()),
                factura.getInfoFactura().getRazonSocialComprador());
    }

    @Override
    public void visit(NotaCredito notaCredito) {
        procesarDoc(
                notaCredito.getInfoTributaria(),
                notaCredito.getInfoNotaCredito().getIdentificacionComprador(),
                notaCredito.getTipoComprobante(),
                DateTimeUtils.parseDate(notaCredito.getInfoNotaCredito().getFechaEmision()),
                DateTimeUtils.parseDateTime(notaCredito.getFechaAutorizacion()),
                notaCredito.getInfoNotaCredito().getRazonSocialComprador());
    }

    @Override
    public void visit(ComprobanteRetencion comprobanteRetencion) {
        procesarDoc(
                comprobanteRetencion.getInfoTributaria(),
                comprobanteRetencion.getInfoCompRetencion().getIdentificacionSujetoRetenido(),
                comprobanteRetencion.getTipoComprobante(),
                DateTimeUtils.parseDate(comprobanteRetencion.getInfoCompRetencion().getFechaEmision()),
                DateTimeUtils.parseDateTime(comprobanteRetencion.getFechaAutorizacion()),
                comprobanteRetencion.getInfoCompRetencion().getIdentificacionSujetoRetenido());
    }

    private void procesarDoc(
            InfoTributaria info,
            String ruc,
            String tipoComprobante,
            LocalDate fechaEmision,
            ZonedDateTime fechaAutorizacion,
            String identificacionReceptor) {
        SriDocEleEmi documento = modelsService.getSriDocByClaveAcceso(info.getClaveAcceso());

        if (documento != null) {
            log.info("Comprobante ya se encuentra registrado en la base de datos");
        } else {
            log.warn("No se encontró el comprobante con la clave de acceso: {}", info.getClaveAcceso());
            Sistema empresa = modelsService.getEmpresaByRuc(ruc);
            if (empresa != null) {
                SriDocEleEmi docSri = crearSriDoc(empresa, info.getClaveAcceso(), tipoComprobante, info.noComprobante(),
                        info.getRuc(),ruc, fechaEmision, fechaAutorizacion,
                        identificacionReceptor);
                System.out.println(docSri);
                if (tipoComprobante.equalsIgnoreCase("Comprobante de Retencion")){
                    log.info("Registro de Comprobante de Retencion agregando al sitema en empresa: {}", empresa.getNombre());
                    //SriDocEleEmi nuevo = modelsService.save(docSri);
                    log.info("Comprobante creado en BD documento -> {}", docSri);
                }else {
                    log.info("Registro de Comprobantes Facturas / Nota de credito ");
                    Cliente proveedor = modelsService.getByRucAndEmpresa(info.getRuc(), empresa.getId());
                    if (proveedor != null) {
                        System.out.println(proveedor);

                    }else {
                        Cliente proveedorNuevo =crearProveedor(info,empresa.getId());
                        System.out.println(proveedorNuevo);
                    }
                }

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

    private Cliente crearProveedor(InfoTributaria info,Long empresa) {
        Cliente proveedor = new Cliente();
        ClienteId id= new ClienteId();
        id.setEmpresa(empresa);
        proveedor.setId(id);
        proveedor.setNombre(reverzarNombre(info.getRazonSocial()));
        proveedor.setRucCedula(info.getRuc());
        proveedor.setTipo((short)2);
        proveedor.setDireccion(info.getDirMatriz());
        return proveedor;
    }

    protected String reverzarNombre(String nombre) {
        String[] partes = nombre.split(" ");

        StringBuilder nuevoNombre = new StringBuilder();

        for (int i = partes.length - 2; i >= 0; i--) {
            nuevoNombre.append(partes[i]).append(" ");
        }

        nuevoNombre.append(partes[partes.length - 1]);

        return nuevoNombre.toString().trim();
    }

}