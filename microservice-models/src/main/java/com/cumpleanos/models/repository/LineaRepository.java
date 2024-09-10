package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Linea;
import com.cumpleanos.models.models.ids.LineaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineaRepository extends JpaRepository<Linea, LineaId> {
}
