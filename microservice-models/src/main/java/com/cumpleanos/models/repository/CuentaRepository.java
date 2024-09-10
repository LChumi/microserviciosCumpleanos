package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Cuenta;
import com.cumpleanos.models.models.ids.CuentaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, CuentaId> {
}
