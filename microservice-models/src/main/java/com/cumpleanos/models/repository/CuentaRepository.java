package com.cumpleanos.models.repository;

import core.cumpleanos.models.entities.Cuenta;
import core.cumpleanos.models.ids.CuentaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, CuentaId> {
}
