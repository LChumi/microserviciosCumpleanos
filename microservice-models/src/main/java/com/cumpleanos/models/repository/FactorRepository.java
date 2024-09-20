package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Factor;
import core.cumpleanos.models.ids.FactorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactorRepository extends JpaRepository<Factor, FactorId> {
}
