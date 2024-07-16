package com.cumlpeanos.pos.repository;

import com.cumlpeanos.pos.models.entity.CajaPOS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CajaPOSRepository extends JpaRepository<CajaPOS, Long> {

    Optional<CajaPOS> findByAlmacenAndPventa(int almacen, int pventa);
}
