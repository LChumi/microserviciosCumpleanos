package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.inmutables.ProductRequest;
import com.cumpleanos.assist.service.http.HttpResponseHandler;
import com.cumpleanos.assist.service.http.IEcommerceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EcommerceClientServiceImpl {

    private final IEcommerceClient ecommerce;

    public Map<String, Object> uploadProduct(ProductRequest product) {
        return HttpResponseHandler.handle(() -> ecommerce.subirProducto(product),
                "Error al subir producto: " + product.sku());
    }

    public Map<String, Object> updateProduct(String sku, ProductRequest product) {
        return HttpResponseHandler.handle(() -> ecommerce.updateProduct(sku, product)
        , "Error al actualizar producto: " + product.sku());
    }
}
