package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Factor;
import com.cumpleanos.core.models.ids.FactorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactorRepository extends JpaRepository<Factor, FactorId> {
}
