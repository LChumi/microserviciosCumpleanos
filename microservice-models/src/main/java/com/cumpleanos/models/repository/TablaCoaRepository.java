package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.TablaCoa;
import core.cumpleanos.models.ids.TablaCoaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablaCoaRepository extends JpaRepository<TablaCoa, TablaCoaId> {
}
