package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.dtos.ProductoDTO;
import com.cumpleanos.core.models.entities.Producto;
import com.cumpleanos.core.models.ids.ProductoId;
import com.cumpleanos.models.persistence.repository.ProductoRepository;
import com.cumpleanos.models.service.interfaces.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ProductoServiceImpl extends GenericServiceImpl<Producto, ProductoId> implements IProductoService {

    private final ProductoRepository repository;

    @Override
    public CrudRepository<Producto, ProductoId> getRepository() {
        return repository;
    }

    @Override
    public ProductoDTO getProductoByBarraAndEmpresa(String barra, Long empresa) {
        Producto prod = repository.findById_EmpresaAndProId(empresa, barra).orElse(null);
        if (prod == null) {
            return null;
        } else {
            return new ProductoDTO(
                    prod.getId().getCodigo(),
                    prod.getId().getEmpresa(),
                    prod.getProId(),
                    prod.getNombre(),
                    prod.getUbicacion(),
                    prod.getProId1(),
                    prod.getCliente(),
                    prod.getProveedor().getId().getCodigo(),
                    prod.getGproducto().getId().getCodigo(),
                    prod.getUnidad().getId().getCodigo()
            );
        }
    }
}

