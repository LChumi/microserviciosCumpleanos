package com.cumpleanos.reccomprobantes.patterns.visitor;

import com.cumpleanos.common.records.ClienteRecord;
import com.cumpleanos.core.models.entities.*;
import com.cumpleanos.core.models.enums.ParametroEnum;
import com.cumpleanos.reccomprobantes.configuration.RutasConfig;
import com.cumpleanos.reccomprobantes.persistence.models.xml.InfoTributaria;
import com.cumpleanos.reccomprobantes.persistence.models.xml.factura.Factura;
import com.cumpleanos.reccomprobantes.persistence.models.xml.notaCredito.NotaCredito;
import com.cumpleanos.reccomprobantes.persistence.models.xml.notaDebito.NotaDebito;
import com.cumpleanos.reccomprobantes.persistence.models.xml.retencion.ComprobanteRetencion;
import com.cumpleanos.reccomprobantes.service.implementation.ModelsServiceImpl;
import com.cumpleanos.reccomprobantes.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ComprobantesProcessor implements ComprobanteVisitor {

    private final ModelsServiceImpl modelsService;
    private final RutasConfig rutasConfig;

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
                comprobanteRetencion.getInfoCompRetencion().getRazonSocialSujetoRetenido());
    }

    @Override
    public void visit(NotaDebito notaDebito) {
        procesarDoc(
                notaDebito.getInfoTributaria(),
                notaDebito.getInfoNotaDebito().getIdentificacionComprador(),
                notaDebito.getTipoComprobante(),
                DateTimeUtils.parseDate(notaDebito.getInfoNotaDebito().getFechaEmision()),
                DateTimeUtils.parseDateTime(notaDebito.getFechaAutorizacion()),
                notaDebito.getInfoNotaDebito().getRazonSocialComprador()
        );
    }

    /**
     * Ciclo de vida del codigo en el se verifica todas las entidades si no existen sigue el ciclo de vida del codigo
     * @param info -> la informacion del comprobnate se usa para llenar la informacion de la mayoria de tablas
     * @param ruc -> el ruc de la empresa donde se va a transdaccionar toda la informacion
     * @param tipoComprobante -> verificacion de tipo de comrpbante (FACTURA - COMPROBANTES DE RETENCION - NOTA DE CREDITO )
     * @param fechaEmision -> La fecha de emision del comprobante
     * @param fechaAutorizacion -> fecha de autorizacion proporcionada por el sri
     * @param identificacionReceptor -> la identificacion del emisor del comprobante
     */
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
                SriDocEleEmi docSri = ComprobantesUtils.crearSriDoc(
                        empresa,
                        info.getClaveAcceso(),
                        tipoComprobante,
                        info.noComprobante(),
                        info.getRuc(),
                        ruc,
                        fechaEmision,
                        fechaAutorizacion,
                        identificacionReceptor);
                log.info("Documento sri nuevo generado :{} ",docSri);

                ClienteRecord proveedor = modelsService.getByRucAndEmpresa(info.getRuc(), (short)2, empresa.getId());
                if (tipoComprobante.equalsIgnoreCase("Comprobante de Retencion")){
                    log.info("Registro de Comprobante de Retencion agregando al sitema en empresa: {}", empresa.getNombre());
                    SriDocEleEmi nuevo = modelsService.save(docSri);
                    log.info("Documento guardado {}",nuevo);
                }else {
                    log.info("Registro de Comprobantes Facturas / Nota de credito ");
                    if (proveedor != null) {
                        saveAndVerifyAutClient(docSri,proveedor.codigo(), empresa, info);
                    }else {
                        log.info("Proveedor no existe agregando {}.....", info.getRuc());
                        Long tipClient= modelsService.verificarJuridico(info.getRuc());
                        Cliente proveedorNuevo = generarProveedorNuevo(info, empresa.getId(), tipClient);
                        System.out.println(rutasConfig.getRutaCliente());

                        FilesUtils utils = new FilesUtils(rutasConfig.getRutaCliente());
                        try {
                            utils.guardarCliente(proveedorNuevo,empresa);
                        }catch (IOException e){
                            log.error("Ocurrio un error al guardar el archivo");
                        }
                        saveAndVerifyAutClient(docSri,proveedorNuevo.getId().getCodigo(), empresa, info);
                    }
                }
            } else {
                log.error("La empresa no existe o el RUC está incorrecto");
            }
        }
    }

    /**
     * Metodo para generar el CliId de la tabla genera por secuencia dependiendo si existen valores en la base de datos
     * @param nombre -> nombre del cliente/proveedor le asigna PN- seguido de las tres primeras letras
     * @param empresa -> la empresa donde se asigna
     * @return -> se retorna la secuencia generada o si no existe la secuencia con 001
     */
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

    /**
     *  Metodo para generar al Provvedor que se va a almacenar en la base de datos
     *  agregando la categoria politica y ciudad
     * @param info -> segun la informacion tributaria de el xml
     * @param empresa -> empresa donde se va a generar el proveedor
     * @param tipCliente -> tipo de cliente verificacion si es juridico o no
     * @return -> cliente guardado
     */
    private Cliente generarProveedorNuevo(InfoTributaria info, Long empresa, Long tipCliente) {

        Cliente cli = ComprobantesUtils.crearProveedor(info,empresa, tipCliente);

        cli.setCliId(generarIdCliente(cli.getNombre(), cli.getId().getEmpresa()));

        cli.setCliCategoria(obtenerParametro(cli.getId().getEmpresa(), ParametroEnum.CXP_CATEGORIA_PROVEEDOR));
        cli.setCliPolitica(obtenerParametro(cli.getId().getEmpresa(), ParametroEnum.CXP_POLITICA_PROVEEDOR));
        cli.setCliCiudad(obtenerParametro(cli.getId().getEmpresa(), ParametroEnum.CXP_CIUDAD_PROVEEDORES));

        return modelsService.save(cli);
    }

    /**
     * Verifica si existe en la tabla AutCliente y si no existe le agrega un nuevo
     * @param docsr -> el documento que se agrego en la tabla doc_sri_emision
     * @param cliente -> el id de cliente que se esta registrando
     * @param sis -> la empresa donde esta registrada toda la informacion
     * @param info -> la informacion tributaria contiene informacion de factura
     */
    private void verificarAutclient(SriDocEleEmi docsr, Long cliente, Sistema sis, InfoTributaria info){
        if (docsr.getId().getNumeroAutorizacion()==null){
            log.warn("El archivo no tiene numero de autorizacion {} en la empresa {}", docsr.getComprobante(), docsr.getId().getEmpresa());
        }
        String numAut = docsr.getId().getNumeroAutorizacion();
        Long empresa = docsr.getId().getEmpresa();

        Autcliente encontrado = modelsService.getAutCliente(numAut, empresa);
        if (encontrado == null) {
            Autcliente autcliente = ComprobantesUtils.crearAutCliente(docsr, cliente, sis, info);
            autcliente.setAclTablacoa(obtenerParametro(empresa,ParametroEnum.COM_COA_TIPOCOM));
            RetDato retDato =modelsService.getRetDato(empresa,autcliente.getAclTablacoa(),info.getCodDoc());
            autcliente.setRetDato(retDato);
            autcliente.getId().setRetdato(retDato.getId().getCodigo());
            autcliente.setValFecha(docsr.getFechaEmision());
            Autcliente nuevo =modelsService.saveAutCliente(autcliente);
            log.info("autcliente nuevo creado: {}", nuevo);
        }else {
            log.info("ya tiene registro en la base de datos documento:{}", docsr.getComprobante());
        }
    }

    /**
     * Metodo para obetener los parametros para la insercion de relaciones en las tablas
     * @param empresa -> La empresa donde se solicita
     * @param parametro -> Enum de los parametros a buscar
     * @return -> devuelve el valor o codigo del Parametro solicitado.
     */
    private Long obtenerParametro(Long empresa, ParametroEnum parametro) {
        return modelsService.verificarParametro(
                empresa,
                parametro.getSigla(),
                parametro.getSecuencia(),
                2
        );
    }

    /**
     * Metodo para guardar en la tabla sri_doc_ele_emi y verificar autcliente
     * @param docSri -> el docuemnto que se va a guardar en la base de datos
     * @param cliCodigo -> el proveedor(Cliente existente o creado)
     * @param empresa -> la empresa donde se va a transaccionar
     * @param info -> Información tributaria donde se obtiene la mayo parte de la información para registros
     */
    private void saveAndVerifyAutClient(SriDocEleEmi docSri, Long cliCodigo, Sistema empresa, InfoTributaria info) {
        SriDocEleEmi nuevo = modelsService.save(docSri);
        verificarAutclient(docSri, cliCodigo, empresa, info);
        log.info("Comprobante creado en BD documento -> {}", nuevo);
    }
}