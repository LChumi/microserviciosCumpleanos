package com.cumlpeanos.pos.persistence.repository;

import com.cumlpeanos.pos.persistence.entity.CajaPOS;
import com.cumlpeanos.pos.persistence.ids.CajaPOSId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CajaPOSRepository extends JpaRepository<CajaPOS, CajaPOSId> {

    Optional<CajaPOS> findByAlmacenAndPventa(Long almacen, Long pventa);
}
