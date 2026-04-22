package com.cumpleanos.mongo.persistence.repository;

import com.cumpleanos.mongo.persistence.models.products.ProductoObservacion;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoObservacionRepository extends MongoRepository<ProductoObservacion, String> {

}