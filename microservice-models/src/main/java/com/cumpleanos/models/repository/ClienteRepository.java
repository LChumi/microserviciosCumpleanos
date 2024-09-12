package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Cliente;
import com.cumpleanos.models.models.ids.ClienteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, ClienteId> {

    Cliente findByRucCedula(String rucCedula);
}
