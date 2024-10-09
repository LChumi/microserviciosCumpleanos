package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.Impuesto;
import com.cumpleanos.core.models.ids.ImpuestoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpuestoRepository extends JpaRepository<Impuesto, ImpuestoId> {
}
