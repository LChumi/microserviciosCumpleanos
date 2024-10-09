package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.Dfactura;
import com.cumpleanos.core.models.ids.DfacturaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DfacturaRepository extends JpaRepository<Dfactura, DfacturaId> {
}
