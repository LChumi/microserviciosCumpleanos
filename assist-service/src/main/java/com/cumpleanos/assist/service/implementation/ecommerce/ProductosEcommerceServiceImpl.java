package com.cumpleanos.assist.service.implementation.ecommerce;

import com.cumpleanos.assist.service.exception.ProductNotCreatedException;
import com.cumpleanos.assist.service.implementation.ClientServiceImpl;
import com.cumpleanos.assist.service.interfaces.ecommerce.IProductosEcommerceService;
import com.cumpleanos.common.builders.ProductoBuilder;
import com.cumpleanos.common.dtos.ProductoDTO;
import com.cumpleanos.common.records.ProductEcomRequest;
import com.cumpleanos.common.records.ServiceResponse;
import com.cumpleanos.core.models.views.CargaProductoEcomV;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.cumpleanos.assist.utils.StringUtils.*;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductosEcommerceServiceImpl implements IProductosEcommerceService {

    private final ClientServiceImpl productoService;
    private final EcommerceClientServiceImpl ecomerceClient;
    private final CargaProductoEcomVServiceImpl cargaProductoEcomV;

    @Override
    public ServiceResponse uploadProductEcommerce(Long id, Long empresa) {
        CargaProductoEcomV pv = cargaProductoEcomV.findByProducto(id, empresa);
        if (pv == null) {
            throw new EntityNotFoundException("No se encontró el producto " + id);
        }

        validateStocks(pv);
        ProductEcomRequest p = viewToProductRequest(pv);
        String message = null;

        Map<String, Object> carga = ecomerceClient.uploadProduct(p);
        if (carga != null && carga.containsKey("id")) {
            Integer prodEcomId = (Integer) carga.get("id");
            log.info("Producto Creado con id: {} ", prodEcomId);
            message = "Producto Creado ";
        } else {
            assert carga != null;
            if (Boolean.TRUE.equals(carga.get("exist"))) {
                log.info("Producto ya existe en WhooCommerce");
                message = "Producto ya existe ";
            } else {
                throw new ProductNotCreatedException("Error al crear el producto en el Ecommerce: " + pv.getProducto());
            }
        }

        ProductoBuilder prod = productoService.findById(pv.getProducto(), empresa);
        if (prod == null) {
            throw new EntityNotFoundException("Producto no encontrado " + pv.getProducto());
        }
        prod.setCargaWeb((short) 2);
        ProductoDTO updated = productoService.updateProducto(prod);
        if (updated == null || updated.codigo() == null) {
            log.info("Producto no se actualizo en la base de datos");
        }
        assert updated != null;
        log.info("{}, {} actualizado en Base de datos {} de la empresa {}", message, p.sku(), updated.codigo(), updated.empresa());
        return new ServiceResponse(message + p.sku() + "En Ecommerce, actualizado en BD", Boolean.TRUE);
    }

    @Override
    public ServiceResponse updateProductEcommerce(Long id, String barraAnt, Long empresa, Integer process) {
        CargaProductoEcomV pv = cargaProductoEcomV.findByProducto(id, empresa);
        if (pv == null) {
            throw new EntityNotFoundException("No se encontró el producto");
        }
        ProductEcomRequest request = viewToProductRequest(pv);

        Map<String, Object> update = ecomerceClient.updateProduct(barraAnt, process, request);
        Integer prodEcomId = (Integer) update.get("id");
        if (prodEcomId == null) {
            throw new ProductNotCreatedException("Error al actualizar el producto en el Ecommerce: " + pv.getProducto());
        }

        return new ServiceResponse("Producto actualizado en Ecommerce", Boolean.TRUE);
    }

    private ProductEcomRequest viewToProductRequest(CargaProductoEcomV pv) {
        return new ProductEcomRequest(
                stringCleaner(pv.getPro_nombre()),
                pv.getPro_id(),
                bigDecimalToString(pv.getPrecio1()),
                "",
                limpiarCategoria(pv.getCategoria()),
                limpiarCategoria(pv.getSubcategoria()),
                longToInteger(pv.getStock()),
                Math.toIntExact(pv.getEmpresa()),
                whitIva(pv.getImpuesto())
        );
    }

    private Boolean whitIva(Long value) {
        return value != null && value == 1;
    }

    private void validateStocks(CargaProductoEcomV v) {
        // Verificar si el stock es válido
        if (v.getStock() < v.getCantMin()) {
            // Si el stock es menor que la cantidad mínima, ajustar el stock a 0
            v.setStock(0L);
        }
        // Si el stock es mayor o igual a la cantidad mínima, no se hace nada
    }
}
