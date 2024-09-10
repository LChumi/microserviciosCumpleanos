package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Dfactura;
import com.cumpleanos.models.models.ids.DfacturaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DfacturaRepository extends JpaRepository<Dfactura, DfacturaId> {
}
