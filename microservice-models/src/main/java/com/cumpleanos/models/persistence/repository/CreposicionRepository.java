package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Creposicion;
import com.cumpleanos.core.models.ids.CreposicionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreposicionRepository extends JpaRepository<Creposicion, CreposicionId> {
}
