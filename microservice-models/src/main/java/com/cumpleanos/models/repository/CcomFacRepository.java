package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.CcomFac;
import core.cumpleanos.models.ids.CcomFacId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CcomFacRepository extends JpaRepository<CcomFac, CcomFacId> {
}
