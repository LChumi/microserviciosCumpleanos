package com.cumpleanos.models.service.implementation;

import com.cumpleanos.common.builders.ProductoPartidaBuilder;
import com.cumpleanos.common.records.ServiceResponse;
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

import java.util.List;

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
        List<ProductoPartida> partidas = repository.getById_ProductoAndId_Empresa(producto, empresa);

        if (partidas == null || partidas.isEmpty()) {
            log.error("Lista vacía para producto {}", producto);
            throw new EntityNotFoundException("No se encontró detalle acerca del producto");
        }

        for (ProductoPartida partida : partidas) {
            if (Boolean.TRUE.equals(partida.getDefaul())) {
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

        // Si no se encontró ninguna partida con defaul=true
        log.warn("No se encontró partida con defaul=true para producto {}", producto);
        throw new EntityNotFoundException("No se encontró partida por defecto para el producto");
    }


    @Override
    public ServiceResponse updatePartidaDefault(Long producto, Long partida, Long empresa) {
        List<ProductoPartida> partidas = repository.getById_ProductoAndId_Empresa(producto, empresa);
        if (partidas == null || partidas.isEmpty()) {
            log.error("No se encontraron partidas para el siguiente {}", producto);
            throw new EntityNotFoundException("No se encontro partidas creadas");
        }
        partidas.forEach(p -> {
            p.setDefaul(p.getId().getPartida().equals(partida));
            repository.save(p);
        });
        return new ServiceResponse("Partidas Actualizadas", true);
    }
}