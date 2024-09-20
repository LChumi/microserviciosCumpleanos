package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Factor;
import core.cumpleanos.models.ids.FactorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactorRepository extends JpaRepository<Factor, FactorId> {
}
