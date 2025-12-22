package com.cumpleanos.assist.service.implementation.importaciones;

import com.cumpleanos.assist.service.interfaces.importaciones.IDmovprodConService;
import com.cumpleanos.common.builders.ProductoPartidaBuilder;
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
import java.util.List;

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

            for (ProductImportTransformer item : request.getItems()) {

                log.info("Buscando informacion del Producto por su id {}", item.getId());
                if (item.getId() == null) {
                    ProductoTemp temporal = productoTempService.getProductoTempByCodFabricaAndEmpresa(item.getCodFabrica(), request.getEmpresa());
                    if (temporal != null) {
                        getDetalleAndAddCant(item, cabecera.cco(), temporal.getCodigo());
                    } else {
                        log.error("Error el producto no existe con el codigo de fabrica {} y no dispone de barra producto {}", item.getCodFabrica(), item.getNombre());
                    }
                } else {
                    ProductoDTO producto = productoService.getProductoByBarraAndEmpresa(item.getId(), request.getEmpresa());
                    if (producto != null) {
                        getDetalleAndAddCant(item, cabecera.cco(), producto.codigo());
                    } else {
                        log.error("Error el producto no existe {} con el id {}", item.getNombre(), item.getId());
                    }
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

        for (ProductImportTransformer item : items) {
            Dfactura detalle = new Dfactura();
            DfacturaId id = new DfacturaId();
            id.setEmpresa(empresa);
            id.setCco(cco);

            detalle.setId(id);
            detalle.setCantidad(item.getCantidadTotal());
            detalle.setCanapr(BigDecimal.ZERO);
            detalle.setPrecio(BigDecimal.valueOf(item.getFob()));

            detalle.setDfacBodega(bodega);
            detalle.setTotal(BigDecimal.valueOf(item.getFobTotal()));
            detalle.setCanent(item.getCxb());
            detalle.setCandev(0);
            detalle.setCanres(0);
            detalle.setDscitem(0);
            detalle.setTraitem(0);

            detalle.setIvaitem(BigDecimal.ZERO);

            detalle.setPdigitado(BigDecimal.valueOf(item.getFob()));
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
                    Long partida = getPartida(producto.codigo(), producto.empresa());
                    if (partida != null) {
                        detalle.setPartida(partida);
                    }
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

    /**
     * Realiza la creación de una relación en la tabla intermedia entre una orden principal y su posible orden SCI.
     * <p>
     * Si ambas órdenes existen, actualiza la cantidad Apr en el detalle de la orden SCI.
     * Si solo existe la orden principal, se crea la relación sin actualización.
     * <p>
     * Lanza una excepción si no se encuentra información en ninguna de las órdenes.
     *
     * @param item     Item de orden creado
     * @param cabecera Código de la orden principal
     * @param producto ID del producto a relacionar
     * @throws IllegalArgumentException si no se encuentra información en ninguna orden
     */
    private void getDetalleAndAddCant(ProductImportTransformer item, BigInteger cabecera, Long producto) {
        List<DfacturaDTO> sciList = productoService.getDfactura(item.getCcoOrigen(), producto);
        List<DfacturaDTO> ordList = productoService.getDfactura(cabecera, producto);

        if (ordList == null || ordList.isEmpty()) {
            throw new IllegalArgumentException(
                    "No existe detalle en ORDEN destino"
            );
        }

        //Orden exacta
        DfacturaDTO orden = seleccionarOrden(ordList, item);

        DfacturaDTO sci = sciList == null || sciList.isEmpty()
                ? null
                : seleccionarSci(sciList, orden.precio());

        //Actualizar CANAPR solo si hay SCI
        if (sci != null) {
            ServiceResponse response =
                    productoService.addedCanApr(sci.cco(), producto, orden.cantidad(), orden.precio());
            if (!response.success()) {
                log.error("No se encontro el mismo valor dentro del registro : {}", response.message());
            }
        }
        createIntermediate(orden, sci, producto);
    }

    /**
     * Crea una relación intermedia entre una orden (orden) y opcionalmente una orden SCI (sci) para el producto especificado.
     * <p>
     * Si sci es nulo, se crea una relación simple con los datos de orden.
     * Si sci no es nulo, se incluye la información adicional de la orden SCI.
     *
     * @param orden    DTO que representa la orden principal
     * @param sci      DTO que representa la orden SCI (puede ser nulo)
     * @param producto ID del producto a relacionar
     * @throws EntityNotFoundException si la entidad no pudo ser creada correctamente
     */
    private void createIntermediate(DfacturaDTO orden, DfacturaDTO sci, Long producto) {

        DmovprodCon relacion = new DmovprodCon();

        asignarDatosBase(relacion, orden, producto);

        if (sci != null) {
            relacion.setEmpresa(sci.empresa());
            relacion.setPrepedido(sci.cco());
            relacion.setPreFecha(sci.fecha());
            relacion.setPreSecuencia(sci.secuencia());
            relacion.setPreCant(sci.cantidad());
        }

        log.info("Realcion:{} ", relacion);
        DmovprodCon intermedia = dmovprodConService.save(relacion);

        if (intermedia == null) {
            throw new EntityNotFoundException("La entidad no fue creada correctamente en el sistema");
        }

        log.info("Creacion de datos en tabla Intermediaria en relacion sci orden creada satisfactoriamente");
    }

    private void asignarDatosBase(DmovprodCon relacion, DfacturaDTO orden, Long producto) {
        relacion.setPedEmpresa(orden.empresa());
        relacion.setProducto(producto);
        relacion.setPedido(orden.cco());
        relacion.setPedSecuencia(orden.secuencia());
        relacion.setPedCant(orden.cantidad());
        relacion.setPedFecha(orden.fecha());
    }

    private Long getPartida(Long producto, Long empresa) {
        ProductoPartidaBuilder partida = productoService.getPartidaByProductoAndEmpresa(producto, empresa);
        if (partida != null) {
            return partida.getPartCodigo();
        }
        return null;
    }

    private DfacturaDTO seleccionarSci(List<DfacturaDTO> sciList, BigDecimal precioOrden) {
        BigDecimal tolerancia = new BigDecimal("1.00");

        return sciList.stream()
                .filter(s -> s.precio() != null)
                .filter(s ->
                        s.precio().subtract(precioOrden).abs().compareTo(tolerancia) <= 0
                )
                .filter(s ->
                        s.cantApr() != null &&
                                s.cantApr().compareTo(BigDecimal.ZERO) == 0
                )
                .findFirst()
                .orElse(null); //SCI puede que no exista
    }

    private DfacturaDTO seleccionarOrden(List<DfacturaDTO> ordenes, ProductImportTransformer item) {
        log.info("Buscando ordenes en el item {}", item);
        log.info("Buscando ordenes en el item {}", ordenes);
        return ordenes.stream()
                .filter(o ->
                        o.precio().compareTo(BigDecimal.valueOf(item.getFob())) == 0 &&
                                o.cantidad().equals(item.getCantidadTotal())
                )
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException("No existe linea de orden con cantidad y precios exactos")
                );
    }
}