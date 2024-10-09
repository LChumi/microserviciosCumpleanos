package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.Cliente;
import com.cumpleanos.core.models.ids.ClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, ClienteId> {

    Optional<Cliente> findById_EmpresaAndRucCedulaAndTipo(Long empresa, String rucCedula, Short tipo);
    List<Cliente> findByCliIdLikeAndId_Empresa(String cliId, Long empresa);
}
