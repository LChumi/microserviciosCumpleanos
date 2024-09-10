package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.TipCliente;
import com.cumpleanos.models.models.ids.TipClienteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipClienteRepository extends JpaRepository<TipCliente, TipClienteId> {
}
