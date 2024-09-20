package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.TablaCoa;
import core.cumpleanos.models.ids.TabalCoaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TablaCoaRepository extends JpaRepository<TablaCoa, TabalCoaId> {
}
