package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.inmutables.ServiceResponse;
import com.cumpleanos.assist.service.interfaces.IProductosEcommerceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductosEcommerceServiceImpl implements IProductosEcommerceService {



    @Override
    public ServiceResponse uploadProductEcommerce(Long id, Long empresa) {
        return null;
    }

    @Override
    public ServiceResponse updateProductEcommerce(Long id, String barraAnt, Long empresa) {
        return null;
    }
}
