package com.cumlpeanos.pos.persistence.repository;

import com.cumlpeanos.pos.persistence.entity.ReciboPOSView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface ReciboPOSViewRepositorio extends JpaRepository<ReciboPOSView, BigInteger> {

    Optional<ReciboPOSView> findByAlmacenAndPventa(Long almacen, Long pventa);
    Optional<ReciboPOSView> findByUsrLiquidaAndEmpresa(Long usrLiquida, Long empresa);
}
