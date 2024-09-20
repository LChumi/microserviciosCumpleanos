package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Dopcion;
import core.cumpleanos.models.ids.DopcionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DopcionRepository extends JpaRepository<Dopcion, DopcionId> {
}
