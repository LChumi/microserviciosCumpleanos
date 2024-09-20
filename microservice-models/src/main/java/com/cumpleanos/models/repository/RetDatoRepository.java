package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.RetDato;
import core.cumpleanos.models.ids.RetDatoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RetDatoRepository extends JpaRepository<RetDato, RetDatoId> {
}
