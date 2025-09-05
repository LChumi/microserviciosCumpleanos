package com.cumpleanos.assist.service.implementation.importaciones;

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
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.entities.ProductoTemp;
import com.cumpleanos.core.models.ids.DfacturaId;
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

    @Override
    public SciResponse procesarSolicitud(SolicitudRequestDTO request) {

        try {
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
        } catch (ProcedureNotCompletedException e) {
            log.error("Error al generar la cabecera SCI: {}", e.getMessage(), e);
            throw e;
        }
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
        for (ProductImportTransformer item: items){
            String clave = item.getItem();

            if (agrupados.containsKey(clave)){
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
            detalle.setCanapr(BigDecimal.ZERO);
            detalle.setPrecio(BigDecimal.valueOf(item.getFob()));

            detalle.setDfacBodega(bodega);
            detalle.setTotal(BigDecimal.valueOf(item.getFobTotal()));
            detalle.setCanent(BigDecimal.ZERO);
            detalle.setCandev(BigDecimal.ZERO);
            detalle.setCanres(BigDecimal.ZERO);
            detalle.setDscitem(BigDecimal.ZERO);
            detalle.setTraitem(BigDecimal.ZERO);

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

}
