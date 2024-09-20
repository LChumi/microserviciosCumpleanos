package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.TipCliente;
import core.cumpleanos.models.ids.TipClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipClienteRepository extends JpaRepository<TipCliente, TipClienteId> {
}
