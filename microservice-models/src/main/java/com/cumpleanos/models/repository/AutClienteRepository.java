package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Autcliente;
import com.cumpleanos.models.models.ids.AutclienteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutClienteRepository extends JpaRepository<Autcliente, AutclienteId> {
}
