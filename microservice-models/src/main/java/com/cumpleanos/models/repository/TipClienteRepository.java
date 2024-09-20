package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.TipCliente;
import core.cumpleanos.models.ids.TipClienteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipClienteRepository extends JpaRepository<TipCliente, TipClienteId> {
}
