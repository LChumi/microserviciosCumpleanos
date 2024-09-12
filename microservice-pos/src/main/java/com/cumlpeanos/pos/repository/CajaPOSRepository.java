package com.cumlpeanos.pos.repository;

import com.cumlpeanos.pos.models.entity.CajaPOS;
import com.cumlpeanos.pos.models.ids.CajaPOSId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CajaPOSRepository extends JpaRepository<CajaPOS, CajaPOSId> {

    Optional<CajaPOS> findByAlmacenAndPventa(Long almacen, Long pventa);
}
