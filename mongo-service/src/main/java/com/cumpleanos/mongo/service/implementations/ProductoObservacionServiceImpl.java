package com.cumpleanos.mongo.service.implementations;

import com.cumpleanos.mongo.persistence.models.products.ProductoObservacion;
import com.cumpleanos.mongo.persistence.models.products.request.CorreccionRequest;
import com.cumpleanos.mongo.persistence.repository.ProductoObservacionRepository;
import com.cumpleanos.mongo.service.exceptions.DocumentNotFoundException;
import com.cumpleanos.mongo.service.interfaces.IProductoObservacionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductoObservacionServiceImpl extends GenericServiceImpl<ProductoObservacion, String> implements IProductoObservacionService {

    private final ProductoObservacionRepository repository;


    @Override
    public CrudRepository<ProductoObservacion, String> getRepository() {
        return repository;
    }

    @Override
    public ProductoObservacion saveObservation(ProductoObservacion p) {
        // recalcular precioTotal
        if (p.getPrecio() != null) {
            p.setPrecioTotal(p.getPrecio().multiply(BigDecimal.valueOf(p.getStock())));
        }

        // buscar si ya existe por item + idBodega
        Optional<ProductoObservacion> existente = repository.findByItemAndIdBodega(p.getItem(), p.getIdBodega());

        if (existente.isPresent()) {
            ProductoObservacion obs = existente.get();
            // actualizar campos necesarios
            obs.setFecha(LocalDate.now()); // actualiza fecha
            obs.setStock(p.getStock());
            obs.setPrecio(p.getPrecio());
            obs.setPrecioTotal(p.getPrecioTotal());
            obs.setUsuario(p.getUsuario());
            obs.setDetalle(p.getDetalle());
            obs.setDiferencia(p.getDiferencia());
            return repository.save(obs);
        } else {
            // insertar nuevo
            return repository.save(p);
        }
    }

    @Override
    public List<ProductoObservacion> findByBodega(Long idBodega) {
        return repository.findByIdBodega(idBodega);
    }

    @Override
    public ProductoObservacion addCorrection(CorreccionRequest request) {
        ProductoObservacion found = repository.findById(request.idProducto()).orElseThrow(
                () -> new DocumentNotFoundException("No se encontro el producto")
        );

        found.setCorreccion(request.correccion());
        return repository.save(found);
    }
}