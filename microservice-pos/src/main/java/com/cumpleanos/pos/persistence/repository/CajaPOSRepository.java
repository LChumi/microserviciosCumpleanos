package com.cumpleanos.pos.persistence.repository;

import com.cumpleanos.pos.persistence.entity.CajaPOS;
import com.cumpleanos.pos.persistence.ids.CajaPOSId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CajaPOSRepository extends JpaRepository<CajaPOS, CajaPOSId> {

    Optional<CajaPOS> findByAlmacenAndPventa(Long almacen, Long pventa);
}
