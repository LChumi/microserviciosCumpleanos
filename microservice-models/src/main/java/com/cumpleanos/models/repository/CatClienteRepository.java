package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.CatCliente;
import com.cumpleanos.models.models.ids.CatClienteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatClienteRepository extends JpaRepository<CatCliente, CatClienteId> {
}
