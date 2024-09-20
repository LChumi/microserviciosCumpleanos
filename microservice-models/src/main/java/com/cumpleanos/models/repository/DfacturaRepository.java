package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Dfactura;
import core.cumpleanos.models.ids.DfacturaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DfacturaRepository extends JpaRepository<Dfactura, DfacturaId> {
}
