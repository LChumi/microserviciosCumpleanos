package com.cumpleanos.mongo.persistence.repository;

import com.cumpleanos.mongo.persistence.models.products.ProductoObservacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoObservacionRepository extends MongoRepository<ProductoObservacion, String> {

    List<ProductoObservacion> findByIdBodega(Long idBodega);

    Optional<ProductoObservacion> findByItemAndIdBodega(String item, Long idBodega);
}