package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.TipCliente;
import com.cumpleanos.core.models.ids.TipClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipClienteRepository extends JpaRepository<TipCliente, TipClienteId> {
}
