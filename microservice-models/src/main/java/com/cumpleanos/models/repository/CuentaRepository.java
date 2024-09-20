package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Cuenta;
import core.cumpleanos.models.ids.CuentaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, CuentaId> {
}
