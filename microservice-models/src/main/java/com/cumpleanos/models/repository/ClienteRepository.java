package com.cumpleanos.models.repository;


import core.cumpleanos.models.entities.Cliente;
import core.cumpleanos.models.ids.ClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, ClienteId> {
    Cliente findByRucCedula(String rucCedula);
}
