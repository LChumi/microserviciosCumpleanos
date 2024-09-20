package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Dfactura;
import core.cumpleanos.models.ids.DfacturaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DfacturaRepository extends JpaRepository<Dfactura, DfacturaId> {
}
