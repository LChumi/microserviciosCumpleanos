package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Impuesto;
import core.cumpleanos.models.ids.ImpuestoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, ImpuestoId> {
}
