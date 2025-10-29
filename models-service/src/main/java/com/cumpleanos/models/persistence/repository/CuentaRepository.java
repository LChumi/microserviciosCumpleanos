package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Cuenta;
import com.cumpleanos.core.models.ids.CuentaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, CuentaId> {
}
