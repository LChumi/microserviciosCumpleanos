package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Dopcion;
import com.cumpleanos.models.models.ids.DopcionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DopcionRepository extends JpaRepository<Dopcion, DopcionId> {
}
