package com.cumpleanos.reccomprobantes.patterns.visitor;

import com.cumpleanos.core.models.entities.Autcliente;
import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.entities.Sistema;
import com.cumpleanos.core.models.entities.SriDocEleEmi;
import com.cumpleanos.core.models.enums.ParametroEnum;
import com.cumpleanos.reccomprobantes.persistence.models.xml.InfoTributaria;
import com.cumpleanos.reccomprobantes.persistence.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.persistence.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.persistence.models.xml.retencion.ComprobanteRetencion;
import com.cumpleanos.reccomprobantes.service.implementation.ModelsServiceImpl;
import com.cumpleanos.reccomprobantes.util.ComprobantesUtils;
import com.cumpleanos.reccomprobantes.util.DateTimeUtils;
import com.cumpleanos.reccomprobantes.util.ProveedorIdGeneratorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ComprobantesProcessor implements ComprobanteVisitor {

    private final ModelsServiceImpl modelsService;

    @Override
    public void visit(Factura factura) {
        System.out.println(factura.getFechaAutorizacion());
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
            log.info("Comprobante ya se encuentra registrado en la base de datos CA:{}", documento.getClaveAcceso());
        } else {
            log.warn("No se encontró el comprobante con la clave de acceso: {}", info.getClaveAcceso());
            Sistema empresa = modelsService.getEmpresaByRuc(ruc);
            if (empresa != null) {
                SriDocEleEmi docSri = ComprobantesUtils.crearSriDoc(empresa, info.getClaveAcceso(), tipoComprobante, info.noComprobante(),
                        info.getRuc(),ruc, fechaEmision, fechaAutorizacion,
                        identificacionReceptor);
                System.out.println(docSri);
                if (tipoComprobante.equalsIgnoreCase("Comprobante de Retencion")){
                    log.info("Registro de Comprobante de Retencion agregando al sitema en empresa: {}", empresa.getNombre());
                    //SriDocEleEmi nuevo = modelsService.save(docSri);
                    log.info("Comprobante creado en BD documento -> {}", docSri);
                }else {
                    log.info("Registro de Comprobantes Facturas / Nota de credito ");
                    Cliente proveedor = modelsService.getByRucAndEmpresa(info.getRuc(), (short)2, empresa.getId());
                    if (proveedor != null) {
                        System.out.println(proveedor);
                        verificarAutclient(docSri,proveedor.getId().getCodigo(),empresa);
                    }else {
                        log.info("Proveedor no existe agregando.....");
                        Long tipClient= modelsService.verificarJuridico(info.getRuc());
                        Cliente proveedorNuevo = generarProveedorNuevo(info, empresa.getId(), tipClient);
                        //Cliente proveedorAgregado= modelsService.save(proveedorNuevo);
                        System.out.println("CLIENTE A AGREGAR ");
                        System.out.println(proveedorNuevo);
                        //SriDocEleEmi nuevo = modelsService.save(docSri);
                        verificarAutclient(docSri,proveedorNuevo.getId().getCodigo(),empresa);
                    }
                }
            } else {
                log.error("La empresa no existe o el RUC está incorrecto");
            }
        }
    }

    private void verificarAutclient(SriDocEleEmi docsr, Long cliente, Sistema sis){
        if (docsr.getId().getNumeroAutorizacion()==null){
            log.warn("El archivo no tiene numero de autorizacion {} en la empresa {}", docsr.getComprobante(), docsr.getId().getEmpresa());
        }
        String numAut = docsr.getId().getNumeroAutorizacion();
        Long empresa = docsr.getId().getEmpresa();

        Autcliente encontrado = modelsService.getAutCliente(numAut, empresa);
        if (encontrado == null) {
            Autcliente nuevo = ComprobantesUtils.crearAutCliente(docsr, cliente, sis);
        }
    }

    private String generarIdCliente(String nombre, Long empresa){

        String nuevoIdBase = ProveedorIdGeneratorUtils.generarPrefix(nombre);

        //Lista de Ids existentes
        List<String> ids = modelsService.getIdsClientes(nuevoIdBase, empresa);

        if (ids.isEmpty()) {
            return nuevoIdBase+"001";
        } else {
            int maxNum =0;
            for (String id : ids) {
                String numStr = id.substring(nuevoIdBase.length());
                int num = Integer.parseInt(numStr);
                if (num > maxNum) {
                    maxNum = num;
                }
            }
            return nuevoIdBase+String.format("%03d", maxNum + 1);
        }
    }

    private Cliente generarProveedorNuevo(InfoTributaria info, Long empresa, Long tipCliente) {

        Cliente cli = ComprobantesUtils.crearProveedor(info,empresa, tipCliente);

        cli.setCliId(generarIdCliente(cli.getNombre(), cli.getId().getEmpresa()));

        cli.setCliCategoria(obtenerParametro(cli, ParametroEnum.CXP_CATEGORIA_PROVEEDOR));
        cli.setCliPolitica(obtenerParametro(cli, ParametroEnum.CXP_POLITICA_PROVEEDOR));
        cli.setCliCiudad(obtenerParametro(cli, ParametroEnum.CXP_CIUDAD_PROVEEDORES));

        return cli;
    }

    private Long obtenerParametro(Cliente cli, ParametroEnum parametro) {
        return modelsService.verificarParametro(
                cli.getId().getEmpresa(),
                parametro.getSigla(),
                parametro.getSecuencia(),
                2
        );
    }

}