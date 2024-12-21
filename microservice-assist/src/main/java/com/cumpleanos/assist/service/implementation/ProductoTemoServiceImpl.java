package com.cumpleanos.assist.service.implementation;

import com.cumpleanos.assist.persistence.entity.ProductoTemp;
import com.cumpleanos.assist.persistence.repository.ProductoTempRepository;
import com.cumpleanos.assist.service.interfaces.IProductoTempService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductoTemoServiceImpl extends GenericServiceImpl<ProductoTemp,Long> implements IProductoTempService {

    private final ProductoTempRepository repository;

    @Override
    public CrudRepository<ProductoTemp, Long> getRepository() {
        return repository;
    }

    @Override
    public ProductoTemp getProductoTempByBarraAndEmpresa(String barra, Long empresa) {
        return repository.findByProIdAndEmpresa(barra, empresa).orElse(null);
    }

    @Override
    public ProductoTemp getProductoTempByBarraOrItem(String barra, Long empresa) {
        return repository.findByEmpresaAndCodFabricaOrProId(empresa, barra).orElse(null);
    }
}
