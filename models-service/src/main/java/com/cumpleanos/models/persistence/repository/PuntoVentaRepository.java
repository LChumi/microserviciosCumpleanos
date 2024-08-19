package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.PuntoVenta;
import com.cumpleanos.core.models.ids.PuntoVentaId;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PuntoVentaRepository extends JpaRepository<PuntoVenta, PuntoVentaId> {

    Set<PuntoVenta> findById_EmpresaAndId_Almacen(@NotNull Long idEmpresa, @NotNull Long idAlmacen);
}
