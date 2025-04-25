package com.cumpleanos.mongo.persistence.repository;

import com.cumpleanos.mongo.persistence.models.EmailGroups;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EmailGroupsRepository extends MongoRepository<EmailGroups, String> {
    Optional<EmailGroups> findByTipo(Long tipo);
}
