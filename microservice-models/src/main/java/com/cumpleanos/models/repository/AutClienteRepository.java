package com.cumpleanos.models.repository;


import core.cumpleanos.models.entities.Autcliente;
import core.cumpleanos.models.ids.AutclienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutClienteRepository extends JpaRepository<Autcliente, AutclienteId> {
}
