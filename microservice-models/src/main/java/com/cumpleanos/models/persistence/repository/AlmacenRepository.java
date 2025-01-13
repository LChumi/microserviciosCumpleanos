package com.cumpleanos.models.persistence.repository;

import com.cumpleanos.core.models.entities.Almacen;
import com.cumpleanos.core.models.ids.AlmacenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, AlmacenId> {
    Set<Almacen> findById_Empresa(Long emnpresa);
}
