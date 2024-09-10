package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Cliente;
import com.cumpleanos.models.models.ids.ClienteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, ClienteId> {
}
