package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Centro;
import core.cumpleanos.models.ids.CentroId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroRepository extends JpaRepository<Centro, CentroId> {
}
