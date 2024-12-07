package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ClienteRepository extends JpaRepository<Cliente, ClienteId> {
    Set<Cliente> findById_EmpresaAndTipo(Long empresa, Short tipo);
}
