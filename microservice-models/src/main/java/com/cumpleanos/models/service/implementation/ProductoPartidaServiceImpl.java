package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.builders.ProductoPartidaBuilder;
import com.cumpleanos.core.models.entities.ProductoPartida;
import com.cumpleanos.core.models.ids.ProductoPartidaId;
import com.cumpleanos.models.persistence.repository.ProductoPartidaRepository;
import com.cumpleanos.models.service.interfaces.IProductoPartidaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ProductoPartidaServiceImpl extends GenericServiceImpl<ProductoPartida, ProductoPartidaId> implements IProductoPartidaService {

    private final ProductoPartidaRepository repository;


    @Override
    public CrudRepository<ProductoPartida, ProductoPartidaId> getRepository() {
        return repository;
    }

    @Override
    public ProductoPartidaBuilder getPartidaBuilder(Long producto, Long empresa) {
        ProductoPartida partida = repository.getById_ProductoAndId_Empresa(producto, empresa);
        if (partida == null) {
            log.error("Partida de producto no encontrada codigo de producto {}", producto);
            throw new EntityNotFoundException("No se encontro detalle acerca del producto");
        }
        return ProductoPartidaBuilder
                .builder()
                .prodCodigo(partida.getId().getProducto())
                .prodNombre(partida.getProducto().getNombre())
                .barra(partida.getProducto().getProId())
                .item(partida.getProducto().getProId1())
                .partCodigo(partida.getId().getPartida())
                .partidaNombre(partida.getPartida().getNombre())
                .partidaId(partida.getPartida().getIprId())
                .porcentaje(partida.getPartida().getPorcentaje())
                .arancel(partida.getPartida().getArancel())
                .build();
    }
}
