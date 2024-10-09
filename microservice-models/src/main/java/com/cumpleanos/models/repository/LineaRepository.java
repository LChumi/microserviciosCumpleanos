package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.Linea;
import com.cumpleanos.core.models.ids.LineaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaRepository extends JpaRepository<Linea, LineaId> {
}
