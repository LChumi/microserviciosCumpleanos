package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.CcomFac;
import com.cumpleanos.core.models.ids.CcomFacId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CcomFacRepository extends JpaRepository<CcomFac, CcomFacId> {
}
