package com.cumpleanos.models.repository;

import com.cumpleanos.core.models.entities.Almacen;
import com.cumpleanos.core.models.ids.AlmacenId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlmacenRepository extends JpaRepository<Almacen, AlmacenId> {
}
