package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.Dopcion;
import com.cumpleanos.core.models.ids.DopcionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DopcionRepository extends JpaRepository<Dopcion, DopcionId> {
}
