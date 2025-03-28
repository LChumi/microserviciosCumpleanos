package com.cumpleanos.assist.service.interfaces;

import com.cumpleanos.core.models.views.StockEcommerceV;

import java.util.List;

public interface IStockEcommerceVService {

    StockEcommerceV findByProductoAndEmpresa(Long codigo , Long empresa);

    List<StockEcommerceV> findAll();
}
