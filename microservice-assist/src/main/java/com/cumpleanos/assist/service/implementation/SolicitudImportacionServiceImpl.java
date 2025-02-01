package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.dto.ProductoDTO;
import com.cumpleanos.assist.persistence.dto.SolicitudRequestDTO;
import com.cumpleanos.assist.persistence.entity.ProductoTemp;
import com.cumpleanos.assist.persistence.inmutables.SciResponse;
import com.cumpleanos.assist.persistence.repository.functions.FunctionOracleRepository;
import com.cumpleanos.assist.persistence.repository.functions.ProcedureOracleRepository;
import com.cumpleanos.assist.persistence.transformers.ProductImportTransformer;
import com.cumpleanos.assist.service.exception.ProcedureNotCompletedException;
import com.cumpleanos.assist.service.http.IModelsClient;
import com.cumpleanos.assist.service.interfaces.IProductoService;
import com.cumpleanos.assist.service.interfaces.IProductoTempService;
import com.cumpleanos.assist.service.interfaces.ISolicitudImportacionService;
import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.ids.DfacturaId;
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
    private final IProductoService productoService;


    @Override
    public SciResponse procesarSolicitud(SolicitudRequestDTO request) {

        try {
            /*BigInteger cco = procedureRepository.getCabeceraIdByProcedure(
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
            );*/
            String ccoStr= "100000000000000000009852231";
            BigInteger cco = new BigInteger(ccoStr);
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
        for (ProductImportTransformer item : items) {
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
