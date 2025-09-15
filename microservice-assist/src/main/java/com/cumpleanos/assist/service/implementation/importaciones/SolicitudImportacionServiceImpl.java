package com.cumpleanos.assist.service.implementation.importaciones;

import com.cumpleanos.assist.service.interfaces.importaciones.IDmovprodConService;
import com.cumpleanos.assist.utils.ProductImportDTOUtils;
import com.cumpleanos.common.dtos.ProductoDTO;
import com.cumpleanos.assist.persistence.dto.SolicitudRequestDTO;
import com.cumpleanos.assist.persistence.inmutables.SciResponse;
import com.cumpleanos.assist.persistence.repository.functions.FunctionOracleRepository;
import com.cumpleanos.assist.persistence.repository.functions.ProcedureOracleRepository;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.service.exception.ProcedureNotCompletedException;
import com.cumpleanos.assist.service.http.IModelsClient;
import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.assist.service.interfaces.importaciones.IProductoTempService;
import com.cumpleanos.assist.service.interfaces.importaciones.ISolicitudImportacionService;
import com.cumpleanos.common.records.DfacturaDTO;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.entities.DmovprodCon;
import com.cumpleanos.core.models.entities.ProductoTemp;
import com.cumpleanos.core.models.ids.DfacturaId;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Slf4j
public class SolicitudImportacionServiceImpl implements ISolicitudImportacionService {

    private final ProcedureOracleRepository procedureRepository;
    private final FunctionOracleRepository functionRepository;
    private final IModelsClient modelsClient;
    private final IProductoTempService productoTempService;
    private final ClientServiceImpl productoService;
    private final IDmovprodConService dmovprodConService;

    @Override
    public SciResponse procesarSolicitud(SolicitudRequestDTO request) {

        try {
            return generarCabeceraYComprobante(request);
        } catch (ProcedureNotCompletedException e) {
            log.error("Error al generar la cabecera SCI: {}", e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public SciResponse procesarOrden(SolicitudRequestDTO request) {
        try {
            SciResponse cabecera = generarCabeceraYComprobante(request);

            //Ingresar datos tabla Intermediaria

            for (ProductImportTransformer item : request.getItems()) {
                ServiceResponse response = new ServiceResponse(null, false);

                log.info("Buscando informacion del Producto en el detalle Dfactura ");
                if (item.getId() == null) {
                    ProductoTemp temporal = productoTempService.getProductoTempByCodFabricaAndEmpresa(item.getCodFabrica(), request.getEmpresa());
                    if (temporal != null) {
                        response = productoService.getDetalleProducto(request.getCcoRef(), temporal.getCodigo());
                    } else {
                        log.error("Error el producto no existe con el codigo de fabrica {} y no dispone de barra producto {}", item.getCodFabrica(), item.getNombre());
                    }

                } else {
                    ProductoDTO producto = productoService.getProductoByBarraAndEmpresa(item.getId(), request.getEmpresa());
                    if (producto != null) {
                        response = productoService.getDetalleProducto(request.getCcoRef(), producto.codigo());
                    } else {
                        log.error("Error el producto no existe {} con el id {}", item.getNombre(), item.getId());
                    }
                }

                if (response.success()) {
                    log.info("El producto se encuentra regisrtado en el detalle del SCI {}", response.message());
                    //ServiceResponse addedCanApr = productoService.addedCanApr()
                }

            }


            return new SciResponse(cabecera.cco(), cabecera.comprobante());
        } catch (ProcedureNotCompletedException e) {
            log.error("Error al generar la cabecera SCI: {}", e.getMessage(), e);
            throw e;
        }
    }

    private SciResponse generarCabeceraYComprobante(SolicitudRequestDTO request) {
        BigInteger cco = procedureRepository.getCabeceraIdByProcedure(
                request.getEmpresa(),
                request.getTipodoc(),
                request.getAlmacen(),
                request.getPventa(),
                request.getSigla(),
                request.getProveedor(),
                request.getUsuario(),
                request.getFecha(),
                request.getModulo(),
                request.getBodega(),
                request.getObservacion()
        );
        createDfacturas(request.getEmpresa(), request.getBodega(), cco, request.getItems());
        String comprobante = getComprobanteCreado(request.getEmpresa(), cco);
        return new SciResponse(cco, comprobante);
    }


    private String getComprobanteCreado(Long empresa, BigInteger coo) {
        try {
            return functionRepository.getComprobante(empresa, coo);
        } catch (Exception e) {
            throw new ProcedureNotCompletedException("Error al obtener el comprobante", e);
        }
    }

    private void createDfacturas(Long empresa, Long bodega, BigInteger cco, List<ProductImportTransformer> items) {
        long cont = 1L;

        Map<String, ProductImportTransformer> agrupados = new HashMap<>();

        log.info("Validando duplicidad de productos y sumando sus valores.....");
        for (ProductImportTransformer item : items) {
            String clave = item.getItem();

            if (agrupados.containsKey(clave)) {
                log.warn("Producto duplicado sumando sus cantidades ");
                ProductImportTransformer existente = agrupados.get(clave);
                existente.setCantidad(existente.getCantidad() + item.getCantidad());
                existente.setCxb(existente.getCxb() + item.getCxb());
                ProductImportDTOUtils.calcularTotales(existente);
            } else {
                agrupados.put(clave, item);
            }
        }

        for (ProductImportTransformer item : agrupados.values()) {
            Dfactura detalle = new Dfactura();
            DfacturaId id = new DfacturaId();
            id.setEmpresa(empresa);
            id.setCco(cco);

            detalle.setId(id);
            detalle.setCantidad((long) item.getCantidadTotal());
            detalle.setCanapr(0);
            detalle.setPrecio(BigDecimal.valueOf(item.getFob()));

            detalle.setDfacBodega(bodega);
            detalle.setTotal(BigDecimal.valueOf(item.getFobTotal()));
            detalle.setCanent(0);
            detalle.setCandev(0);
            detalle.setCanres(0);
            detalle.setDscitem(0);
            detalle.setTraitem(0);

            detalle.setIvaitem(BigDecimal.ZERO);

            detalle.setCantini(BigDecimal.valueOf(item.getCantidadTotal()));
            detalle.setCdigitada(BigDecimal.valueOf(item.getCantidadTotal()));
            if (item.getId() == null) {
                ProductoTemp temporal = productoTempService.getProductoTempByCodFabricaAndEmpresa(item.getCodFabrica(), empresa);
                if (temporal != null) {
                    detalle.setProductTemp(temporal.getCodigo());
                }
            } else {
                ProductoDTO producto = productoService.getProductoByBarraAndEmpresa(item.getId(), empresa);
                if (producto != null) {
                    detalle.setDfacProducto(producto.codigo());
                }
            }
            if (detalle.getDfacProducto() != null || detalle.getProductTemp() != null) {
                id.setSecuencia(cont++);
                log.info("detalle creado \n{}", detalle);
                Boolean save = modelsClient.create(detalle).getBody();
                if (Boolean.FALSE.equals(save)) {
                    log.error("Error al crear la dfactura en la empresa: {} en el cco: {}", empresa, cco);
                }
            }
        }
    }

    private void getDetalleAndAddCant(BigInteger ccoAnt, BigInteger cabecera, Long producto){

        DfacturaDTO sci = productoService.getDfactura(ccoAnt, producto);
        DfacturaDTO ord = productoService.getDfactura(cabecera, producto);

        if (sci == null){
            log.info("El producto no tiene origen de datos en detalle.");
        }else{
            if (ord == null){
                log.info("Detalle no existente revise la informacion cco: {} , ccoAnt: {} del producto {}", cabecera, ccoAnt, producto);
                throw new IllegalArgumentException("Detalle no existente revise la informacion" );
            }
            ServiceResponse response = productoService.addedCanApr(sci.cco(),producto, Math.toIntExact(ord.cantidad()));
            if (response.success()){
                log.info("Detalle de producto actualizado correctamente cantidad Apr.");

            }
        }

    }

    private DmovprodCon create (DfacturaDTO orden, DfacturaDTO sci, Long producto){
        if (sci == null){
            DmovprodCon relacion = new DmovprodCon();
            relacion.setEmpresa(orden.empresa());
            relacion.setProducto(producto);
            relacion.setPrepedido(orden.cco());
            relacion.setPreSecuencia(orden.secuencia());
            relacion.setPreCant(orden.cantidad());
            relacion.setPreFecha(orden.fecha());
            DmovprodCon intermedia = dmovprodConService.save(relacion);
            if (intermedia == null){
                log.error("Error no se pudo ingresar el detalle a la tabla intermedia cco{}", orden.cco());
                throw new EntityNotFoundException("La entidad no fue creada correctamente");
            }
        } else {
            DmovprodCon relacion = new DmovprodCon();
            relacion.setEmpresa(orden.empresa());
            relacion.setProducto(producto);
            relacion.setPrepedido(orden.cco());
            relacion.setPreSecuencia(orden.secuencia());
            relacion.setPreCant(orden.cantidad());
            relacion.setPreFecha(orden.fecha());
            //SCI
            relacion.
        }
    }

}
