package com.cumpleanos.mongo.persistence.repository;

import com.cumpleanos.mongo.persistence.models.products.ProductoObservacion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductoObservacionRepository extends MongoRepository<ProductoObservacion, String> {

    List<ProductoObservacion> findByIdBodega(Long idBodega);
}