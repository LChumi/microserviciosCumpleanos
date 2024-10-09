package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.CatCliente;
import com.cumpleanos.core.models.ids.CatClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatClienteRepository extends JpaRepository<CatCliente, CatClienteId> {
}
