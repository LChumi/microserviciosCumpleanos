package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.CatCliente;
import core.cumpleanos.models.ids.CatClienteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatClienteRepository extends JpaRepository<CatCliente, CatClienteId> {
}
