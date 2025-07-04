package com.cumpleanos.assist.service.implementation.importaciones;

import com.cumpleanos.assist.persistence.repository.ProductoTempRepository;
import com.cumpleanos.assist.service.implementation.GenericServiceImpl;
import com.cumpleanos.assist.service.interfaces.importaciones.IProductoTempService;
import com.cumpleanos.core.models.entities.ProductoTemp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductoTempServiceImpl extends GenericServiceImpl<ProductoTemp, Long> implements IProductoTempService {

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
    public ProductoTemp getProductoTempByCodFabricaAndEmpresa(String codFabrica, Long empresa) {
        return repository.findByCodFabricaAndEmpresa(codFabrica, empresa).orElse(null);
    }

    @Override
    public ProductoTemp getProductoTempByBarraOrItem(String barra, Long empresa) {
        return repository.findByEmpresaAndCodFabricaOrProId(empresa, barra).orElse(null);
    }
}
