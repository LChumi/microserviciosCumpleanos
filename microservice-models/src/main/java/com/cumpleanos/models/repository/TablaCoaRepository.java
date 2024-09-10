package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.TablaCoa;
import com.cumpleanos.models.models.ids.TabalCoaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TablaCoaRepository extends JpaRepository<TablaCoa, TabalCoaId> {
}
