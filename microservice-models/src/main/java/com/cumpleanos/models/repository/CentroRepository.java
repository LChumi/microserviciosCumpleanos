package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Centro;
import com.cumpleanos.models.models.ids.CentroId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentroRepository extends JpaRepository<Centro, CentroId> {
}
