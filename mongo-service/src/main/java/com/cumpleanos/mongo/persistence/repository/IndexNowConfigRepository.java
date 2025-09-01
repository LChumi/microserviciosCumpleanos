package com.cumpleanos.mongo.persistence.repository;

import com.cumpleanos.mongo.persistence.models.app.IndexNowConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IndexNowConfigRepository extends MongoRepository<IndexNowConfig, String> {

    Optional<IndexNowConfig> getByAppName(String appName);

}