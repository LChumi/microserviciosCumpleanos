package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Centro;
import com.cumpleanos.core.models.ids.CentroId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroRepository extends JpaRepository<Centro, CentroId> {
}
