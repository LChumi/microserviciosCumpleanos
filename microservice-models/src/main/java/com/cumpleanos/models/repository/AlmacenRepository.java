package com.cumpleanos.models.repository;

import com.cumpleanos.models.models.entities.Almacen;
import com.cumpleanos.models.models.ids.AlmacenId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlmacenRepository extends JpaRepository<Almacen, AlmacenId> {
}
