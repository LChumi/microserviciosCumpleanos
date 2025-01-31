package com.cumpleanos.assist.persistence.repository;

import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, ClienteId> {
    Set<Cliente> findById_EmpresaAndTipo(Long empresa, Short tipo);
    Set<Cliente> findById_empresaAndTipoAndCliCategoria(Long empresa, Short tipo, Long categoria);
}
