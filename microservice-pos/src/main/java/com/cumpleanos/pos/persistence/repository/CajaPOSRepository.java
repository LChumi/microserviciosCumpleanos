package com.cumpleanos.pos.persistence.repository;

import com.cumpleanos.pos.persistence.entity.CajaPOS;
import com.cumpleanos.pos.persistence.ids.CajaPOSId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CajaPOSRepository extends JpaRepository<CajaPOS, CajaPOSId> {

    Optional<CajaPOS> findByAlmacenAndPventa(Long almacen, Long pventa);
}
