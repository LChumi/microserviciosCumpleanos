package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Umedida;
import core.cumpleanos.models.ids.UmedidaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UmedidaRepository extends JpaRepository<Umedida, UmedidaId> {
}
