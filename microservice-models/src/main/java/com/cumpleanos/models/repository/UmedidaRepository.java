package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.Umedida;
import com.cumpleanos.core.models.ids.UmedidaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UmedidaRepository extends JpaRepository<Umedida, UmedidaId> {
}
