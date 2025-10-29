package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.TablaCoa;
import com.cumpleanos.core.models.ids.TablaCoaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablaCoaRepository extends JpaRepository<TablaCoa, TablaCoaId> {
}
