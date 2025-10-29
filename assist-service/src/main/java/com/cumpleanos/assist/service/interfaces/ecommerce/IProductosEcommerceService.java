package com.cumpleanos.assist.service.interfaces.ecommerce;

import com.cumpleanos.common.records.ServiceResponse;

public interface IProductosEcommerceService {

    ServiceResponse uploadProductEcommerce(Long id, Long empresa);

    ServiceResponse updateProductEcommerce(Long id,String barraAnt, Long empresa, Integer process);
}
