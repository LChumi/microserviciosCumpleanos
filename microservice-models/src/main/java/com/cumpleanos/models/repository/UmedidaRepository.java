package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Umedida;
import com.cumpleanos.models.models.ids.UmedidaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UmedidaRepository extends JpaRepository<Umedida, UmedidaId> {
}
