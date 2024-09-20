package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Ccomproba;
import core.cumpleanos.models.ids.CcomprobaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CcomprobaRepository extends JpaRepository<Ccomproba, CcomprobaId> {
}
