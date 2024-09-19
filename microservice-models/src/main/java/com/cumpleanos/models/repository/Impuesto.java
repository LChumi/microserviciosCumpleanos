package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.ids.ImpuestoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Impuesto extends JpaRepository<Impuesto, ImpuestoId> {
}
