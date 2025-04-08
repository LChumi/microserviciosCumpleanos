package com.cumpleanos.assist.service.implementation.ecommerce;

import com.cumpleanos.assist.service.http.HttpResponseHandler;
import com.cumpleanos.assist.service.http.IEcommerceClient;
import com.cumpleanos.common.records.ProductEcomRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EcommerceClientServiceImpl {

    private final IEcommerceClient ecommerce;

    public Map<String, Object> uploadProduct(ProductEcomRequest product) {
        return HttpResponseHandler.handle(() -> ecommerce.subirProducto(product),
                "Error al subir producto: " + product.sku());
    }

    public Map<String, Object> updateProduct(String sku, Integer process, ProductEcomRequest product) {
        return HttpResponseHandler.handle(() -> ecommerce.updateProduct(sku, process, product),
                "Error al actualizar producto: " + product.sku());
    }

    public List<Map<String, Object>> getOrdesrByDate(LocalDate d1, LocalDate d2) {
        return HttpResponseHandler.handle(() -> ecommerce.getOrdersDate(d1, d2),
                "Error al obtener los pedidos");
    }
}
