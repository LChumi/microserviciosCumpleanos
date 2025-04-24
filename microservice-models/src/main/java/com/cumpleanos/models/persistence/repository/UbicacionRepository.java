package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Ubicacion;
import com.cumpleanos.core.models.ids.UbicacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, UbicacionId> {

    List<Ubicacion> findById_EmpresaAndNombreLikeIgnoreCase(Long idEmpresa, String nombre);
}
