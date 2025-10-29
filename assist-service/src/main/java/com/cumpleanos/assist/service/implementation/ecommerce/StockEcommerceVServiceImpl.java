package com.cumpleanos.assist.service.implementation.ecommerce;

import com.cumpleanos.assist.persistence.repository.StockEcommerceVRepository;
import com.cumpleanos.assist.service.interfaces.ecommerce.IStockEcommerceVService;
import com.cumpleanos.core.models.views.StockEcommerceV;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class StockEcommerceVServiceImpl implements IStockEcommerceVService {

    private final StockEcommerceVRepository repository;

    @Override
    public StockEcommerceV findByProductoAndEmpresa(Long producto, Long empresa) {
        return repository.findByProductoAndEmpresa(producto, empresa);
    }

    @Override
    public List<StockEcommerceV> findAll() {
        return repository.findAll();
    }
}
