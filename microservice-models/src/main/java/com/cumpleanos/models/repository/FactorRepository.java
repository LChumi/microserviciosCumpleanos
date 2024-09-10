package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Factor;
import com.cumpleanos.models.models.ids.FactorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactorRepository extends JpaRepository<Factor, FactorId> {
}
