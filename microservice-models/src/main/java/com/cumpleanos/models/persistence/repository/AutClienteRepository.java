package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Autcliente;
import com.cumpleanos.core.models.ids.AutclienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutClienteRepository extends JpaRepository<Autcliente, AutclienteId> {

    Optional<Autcliente> findById_NroAutorizaAndId_Empresa(String nroAutoriza, Long empresa);
}
