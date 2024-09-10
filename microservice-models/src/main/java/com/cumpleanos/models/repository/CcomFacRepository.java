package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.CcomFac;
import com.cumpleanos.models.models.ids.CcomFacId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CcomFacRepository extends JpaRepository<CcomFac, CcomFacId> {
}
