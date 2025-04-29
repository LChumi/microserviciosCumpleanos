package com.cumpleanos.mongo.persistence.repository;

import com.cumpleanos.mongo.persistence.models.company.CompanyParameters;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyParametersRepository extends MongoRepository<CompanyParameters, String> {
    Optional<CompanyParameters> findByCompanyId(Long companyId);
}
