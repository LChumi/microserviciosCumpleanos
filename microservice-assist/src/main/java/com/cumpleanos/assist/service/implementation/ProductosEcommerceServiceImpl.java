package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.inmutables.ProductRequest;
import com.cumpleanos.assist.persistence.inmutables.ServiceResponse;
import com.cumpleanos.assist.persistence.repository.ProductoRepository;
import com.cumpleanos.assist.service.exception.ProductNotCreatedException;
import com.cumpleanos.assist.service.interfaces.IProductoService;
import com.cumpleanos.assist.service.interfaces.IProductosEcommerceService;
import com.cumpleanos.assist.utils.StringUtils;
import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.ids.ProductoId;
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

    private final IProductoService productoService;
    private final EcommerceClientServiceImpl ecomerceClient;
    private final CargaProductoEcomVServiceImpl cargaProductoEcomV;

    @Override
    public ServiceResponse uploadProductEcommerce(Long id, Long empresa) {
        CargaProductoEcomV pv = cargaProductoEcomV.findByProducto(id, empresa);
        if (pv == null) {
            throw new EntityNotFoundException("No se encontró el producto");
        }

        validateStocks(pv);
        ProductRequest p = viewToProductRequest(pv);

        Map<String, Object> carga = ecomerceClient.uploadProduct(p);
        if (carga != null && carga.containsKey("id")) {
            Integer prodEcomId=(Integer) carga.get("id");
            log.info("Producto Creado con id: {} " , prodEcomId);
        }else {
            throw new ProductNotCreatedException("Error al crear el producto en el Ecommerce: " + pv.getProducto());
        }


        ProductoId proId = new ProductoId();
        proId.setCodigo(pv.getProducto());
        proId.setEmpresa(empresa);
        Producto prod = productoService.findById(proId);
        if (prod == null) {
            throw new EntityNotFoundException("Producto no encontrado");
        }
        prod.setCargaWeb((short) 2);
        productoService.save(prod);
        return new ServiceResponse("Producto creado "+ p.sku() + "En Ecommerce y actualizado en BD", Boolean.TRUE );
    }

    @Override
    public ServiceResponse updateProductEcommerce(Long id, String barraAnt, Long empresa) {
        CargaProductoEcomV pv = cargaProductoEcomV.findByProducto(id, empresa);
        if (pv == null) {
            throw new EntityNotFoundException("No se encontró el producto");
        }
        ProductRequest p = viewToProductRequest(pv);

        Map<String,Object> update = ecomerceClient.updateProduct(barraAnt, p);
        Integer prodEcomId=(Integer) update.get("id");
        if (prodEcomId == null) {
            throw new ProductNotCreatedException("Error al actualizar el producto en el Ecommerce: " + pv.getProducto());
        }

        return new ServiceResponse("Producto actualizado en Ecommerce", Boolean.TRUE );
    }

    private ProductRequest viewToProductRequest(CargaProductoEcomV pv) {
        return new ProductRequest(
                stringCleaner(pv.getPro_nombre()),
                pv.getPro_id(),
                bigDecimalToString(pv.getPrecio1()),
                "",
                stringCleaner(pv.getCategoria()),
                stringCleaner(pv.getSubcategoria()),
                longToInteger(pv.getStock()),
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
