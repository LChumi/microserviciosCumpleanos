package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.assist.persistence.inmutables.ServiceResponse;

public interface IProductosEcommerce {

    ServiceResponse uploadProductEcommerce(Long id, Long empresa);

    ServiceResponse updateProductEcommerce(Long id,String barraAnt, Long empresa);
}
